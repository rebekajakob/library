package com.rebekajakob.library.controller;


import com.rebekajakob.library.model.Author;
import com.rebekajakob.library.model.Book;
import com.rebekajakob.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();
    }

    @PostMapping
    public void addBook(@RequestBody Book book){
        bookService.addBook(book);
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable String bookId){
        return bookService.getBookById(bookId);
    }

    @PutMapping("/{bookId}")
    public void updateBook(@PathVariable String bookId, @RequestBody Book book){
        bookService.updateBook(bookId,book);
    }

    @PostMapping("/{bookId}/author")
    public void addAuthorToBook(@PathVariable String bookId, @RequestBody Author author){
        bookService.addAuthorToBook(bookId,author);
    }





}
