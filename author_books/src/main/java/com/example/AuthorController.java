package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public void getAllAuthorWithBooks(Long id) {
        authorService.getAllAuthorsWithBooks();
    }

    /*@GetMapping("/nplusone")
    public void getAllAuthorWithBooksNPlusOne(Long id) {
        authorService.getAllAuthorWithBooksNPlusOne();
    }
    @GetMapping("/entitygraph")
    public void getAllAuthorWithBooksEntitygraph(Long id) {
        authorService.getAllAuthorWithBooksEntitygraph();
    }

    @GetMapping("/joinfetch")
    public void getAllAuthorWithBooksJoinFetch(Long id) {
        authorService.getAllAuthorWithBooksJoinFetch();
    }*/
}
