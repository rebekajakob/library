package com.rebekajakob.library.controller;

import com.rebekajakob.library.model.Author;
import com.rebekajakob.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors(){
        return authorService.getAllAuthors();
    }

    @PostMapping
    public void addAuthor(@RequestBody Author author){
        authorService.addAuthor(author);
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable String authorId){
        return authorService.getAuthorByID(authorId);
    }

    @PutMapping("/{authorId}")
    public void updateAuthor(@PathVariable String authorId, @RequestBody Author author){
        authorService.updateAuthor(authorId,author);
    }
}
