package com.rebekajakob.library.controller;

import com.rebekajakob.library.model.Author;
import com.rebekajakob.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addAuthor(@RequestBody Author author){
        Author newAuthor = authorService.addAuthor(author);
        if(newAuthor!= null){
            return ResponseEntity.ok(newAuthor);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry, we couldn't save this author " + author);
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<?> getAuthorById(@PathVariable String authorId){
        Author author = authorService.getAuthorByID(authorId);
        if(author != null){
            return ResponseEntity.ok(author);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please add an existing author id!");
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<?> updateAuthor(@PathVariable String authorId, @RequestBody Author author){
        Author updatedAuthor = authorService.updateAuthor(authorId,author);
        if(updatedAuthor!= null){
            return ResponseEntity.ok(updatedAuthor);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please add an existing Author id!");
    }
}
