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

    public Author addAuthor(Author author){
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }

    public Author getAuthorByID(String authorId){
        if (authorRepository.findById(UUID.fromString(authorId)).isPresent()){
            return authorRepository.findById(UUID.fromString(authorId)).get();
        }
        return null;
    }

    public Author updateAuthor(String authorId, Author author){
        Author currentAuthor;
        if (authorRepository.findById(UUID.fromString(authorId)).isPresent()) {
            currentAuthor = authorRepository.findById(UUID.fromString(authorId)).get();
        }else{
            return null;
        }
        if(author.getBirthday() != null && !author.getBirthday().equals(currentAuthor.getBirthday())){
            currentAuthor.setBirthday(author.getBirthday());
        }
        if(author.getName() != null && !author.getName().equals(currentAuthor.getName())){
            currentAuthor.setName(author.getName());
        }
        return authorRepository.save(currentAuthor);
    }
}
