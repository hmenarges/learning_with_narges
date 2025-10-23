# **Hibernate N+1 Query Problem: A Practical Demonstration**

This project demonstrates the classic "N+1 query problem" in Hibernate/JPA and illustrates two effective solutions to prevent it: `@EntityGraph` and `JOIN FETCH`.

## **The Core Problem**

The N+1 query problem occurs when you fetch a list of entities (the "1" query) and then lazily access a related collection for *each* of those entities, resulting in "N" additional queries (one for each parent entity). This is highly inefficient and can severely degrade application performance.

### **Our Entities**

We have two entities: `Author` and `Book`, with a one-to-many relationship.

#### **Author.java**
The Author entity has a list of Book entities. Crucially, this relationship is `FetchType.LAZY` by default, which is the setup for the N+1 problem.  
``` java
@Entity  
public class Author {  
    @Id  
    @GeneratedValue  
    private Long id;  
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)  
    private List<Book> bookList;

    // ... constructors, getters, and setters  
}
```

#### **Book.java**  
The Book entity has a ManyToOne relationship back to its Author.
``` java
@Entity  
public class Book {  
@Id  
@GeneratedValue  
private Long id;  
private String name;

    @ManyToOne  
    @JoinColumn(name = "author_id")  
    private Author author;

    // ... constructors, getters, and setters  
}
```

## **1. Reproducing the N+1 Problem**

We can easily reproduce the problem by fetching all authors and then accessing their list of books.

#### **Repository (AuthorRepository.java)**  
First, we define a simple query that only fetches the authors.  
``` java
@Repository  
public interface AuthorRepository extends JpaRepository<Author, Long> {  
// ... other methods ...

    @Query("SELECT a FROM Author a")  
    List<Author> findAllWithproblem();  
}
```

#### **Service (AuthorServiceImpl.java)**
In our service, we call this method and then iterate over the results, accessing the bookList. This access triggers the lazy-loading.  

``` java
@Service  
public class AuthorServiceImpl implements AuthorService {  
// ... constructor ...

    @Override  
    public List<Author> getAllAuthorsWithBooks() {  
        log.info("fetch authors with books - N+1 problem ");  
        List<Author> authors = authorRepository.findAllWithproblem();  
          
        // This loop triggers N additional queries  
        for (Author author : authors) {  
            // Accessing .getBookList() fires a new query for each author  
            author.getBookList().size();   
        }  
          
        // ...  
    }  
}
```

**What Happens:**

1. **Query 1:** `SELECT a FROM Author a` (or select * from author)
2. **Query 2:** `SELECT * FROM book WHERE author_id=?` (for the first author)
3. **Query 3:** `SELECT * FROM book WHERE author_id=?` (for the second author)
4. ...
5. **Query N+1:** `SELECT * FROM book WHERE author_id=?` (for the Nth author)

This is the N+1 problem.

## **2. Solving the N+1 Problem**

We can solve this by telling Hibernate to fetch the related bookList in the *initial* query. This is called "eager fetching."

### **Solution 1: Using @EntityGraph**

`@EntityGraph` is a clean, modern way to specify which associations to fetch eagerly. We can add it directly to our Spring Data JPA repository method.

#### **Repository (AuthorRepository.java)**
By adding `@EntityGraph(attributePaths = "bookList")`, we tell JPA to include the bookList in its query plan. This typically results in a `LEFT JOIN`.  
``` java
@Repository  
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @EntityGraph(attributePaths = "bookList")  
    List<Author> findAll(); // We can just override the default findAll()

    // ...  
}
```

#### **Service (AuthorServiceImpl.java)**

Now when we call this method, no N+1 problem occurs.  
``` java
    //...
    
    log.info("fetch authors with books - avoid N+1 problem using EntiyGraph");  
    authors = authorRepository.findAll(); // This method uses the @EntityGraph  
    for (Author author : authors) {  
    // No new query is fired. The data is already loaded.  
        author.getBookList().size();  
    }
    
    //...
```

**What Happens:**

* **Query 1:** A single, more complex query is executed, something like: 
``` SQL
select ... from author a left outer join book b on a.id=b.author_id
```

### **Solution 2: Using JOIN FETCH**

We can also be explicit in our JPQL query by using the `JOIN FETCH` keywords. This gives us direct control over the query.

##### **Repository (AuthorRepository.java)**
The `JOIN FETCH a.bookList` clause instructs Hibernate to fetch the authors and their associated books in a single query.  
``` java
@Repository  
public interface AuthorRepository extends JpaRepository<Author, Long> {  
// ...

    @Query("SELECT a FROM Author a JOIN FETCH a.bookList")  
    List<Author> findAllWithJoin();

    // ...  
}
```

##### **Service (AuthorServiceImpl.java)**
This method also avoids the N+1 problem completely.  
``` java
    log.info("fetch authors with books - avoid N+1 problem using join fetch");  
    authors = authorRepository.findAllWithJoin();  
    for (Author author : authors) {  
        // No new query is fired. The data is already loaded.  
        author.getBookList().size();  
    }
```

**What Happens:**

* **Query 1:** A single query is executed, similar to the one generated by `@EntityGraph`.

## **Conclusion**

Both @EntityGraph and JOIN FETCH are excellent solutions to the N+1 query problem.

* Use `@EntityGraph` for a cleaner, more "Spring Data" idiomatic way to define eager fetching on repository methods.
* Use `JOIN FETCH` when you need more fine-grained control over the JPQL query itself.

By proactively identifying and solving N+1 issues, you can ensure your data access layer remains fast and efficient.