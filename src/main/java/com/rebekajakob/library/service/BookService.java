package com.rebekajakob.library.service;

import com.rebekajakob.library.model.Author;
import com.rebekajakob.library.model.Book;
import com.rebekajakob.library.repository.AuthorRepository;
import com.rebekajakob.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void addBook(Book book){
        bookRepository.save(book);
    }

    public void addAuthorToBook(String bookId, Author author){
        Book currentBook =  bookRepository.findById(UUID.fromString(bookId)).get();
        Author currentAuthor = authorRepository.findById(author.getId()).get();
        currentBook.setAuthor(currentAuthor);
        bookRepository.save(currentBook);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }
}
