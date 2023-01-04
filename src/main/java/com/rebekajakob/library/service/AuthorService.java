package com.rebekajakob.library.service;

import com.rebekajakob.library.model.Author;
import com.rebekajakob.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorByID(String authorId){
        return authorRepository.findById(UUID.fromString(authorId)).get();
    }

    public void updateAuthor(String authorId, Author author){
        Author currentAuthor = authorRepository.findById(UUID.fromString(authorId)).get();
        if(author.getBirthday() != null && !author.getBirthday().equals(currentAuthor.getBirthday())){
            currentAuthor.setBirthday(author.getBirthday());
        }
        if(author.getName() != null && !author.getName().equals(currentAuthor.getName())){
            currentAuthor.setName(author.getName());
        }
        authorRepository.save(currentAuthor);
    }
}
