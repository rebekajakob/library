package com.rebekajakob.library.service;

import com.rebekajakob.library.model.Author;
import com.rebekajakob.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void addAuthor(Author author){
        authorRepository.save(author);
    }
}
