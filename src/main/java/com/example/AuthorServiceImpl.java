package com.example;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthorsWithBooks() {
        List<Author> authors = null;
        log.info("fetch authors with books - N+1 problem ");
        authors = authorRepository.findAllWithproblem();
        for (Author author : authors) {
            author.getBookList().size();
        }
        log.info("fetch authors with books - avoid N+1 problem using EntiyGraph");
        authors = authorRepository.findAll();
        for (Author author : authors) {
            author.getBookList().size();
        }
        log.info("fetch authors with books - avoid N+1 problem using join fetch");
        authors = authorRepository.findAllWithJoin();
        for (Author author : authors) {
            author.getBookList().size();
        }
        return authors;

    }
}
