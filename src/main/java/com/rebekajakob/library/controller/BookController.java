package com.rebekajakob.library.controller;


import com.rebekajakob.library.model.Author;
import com.rebekajakob.library.model.Book;
import com.rebekajakob.library.model.LibraryUser;
import com.rebekajakob.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> addBook(@RequestBody Book book){
        Book newBook = bookService.addBook(book);
        if (newBook== null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry, we couldn't save this book " + book);
        }
        return ResponseEntity.ok(newBook.toString());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<String> getBookById(@PathVariable String bookId){
        Book book = bookService.getBookById(bookId);
        if(book==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please add an existing book id!");
        }
        return ResponseEntity.ok(book.toString());
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable String bookId, @RequestBody Book book){
        Book updatedBook = bookService.updateBook(bookId,book);
        if(updatedBook==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please add an existing book id!");
        }
        return ResponseEntity.ok(updatedBook.toString());
    }

    @PostMapping("/{bookId}/author")
    public ResponseEntity<String> addAuthorToBook(@PathVariable String bookId, @RequestBody Author author){
        Book book = bookService.addAuthorToBook(bookId,author);
        if(book==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please add an existing book id and existing author id in the request body!");
        }
        return ResponseEntity.ok(book.toString());
    }

    @PostMapping("/{bookId}/reserve")
    public ResponseEntity<String> reserveBook(@PathVariable String bookId, @RequestBody LibraryUser libraryUser){
        int reserveStatus = bookService.reserveBook(bookId,libraryUser);
        return switch (reserveStatus) {
            case 0 -> ResponseEntity.ok("You reserved this book!");
            case 1 -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please add an existing book id!");
            case 2 ->
                    ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please add an existing user id in the request body!");
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This book is already reserved!");
        };
    }

    @GetMapping("/available")
    public List<Book> getAvailableBooks(){
        return bookService.getAvailableBooks();
    }
}
