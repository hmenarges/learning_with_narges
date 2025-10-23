package com.example;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    @EntityGraph(attributePaths = "bookList")
    List<Author> findAll();

    @Query("SELECT a FROM Author a JOIN FETCH a.bookList")
    List<Author> findAllWithJoin();

    @Query("SELECT a FROM Author a")
    List<Author> findAllWithproblem();

}
