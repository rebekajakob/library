package com.rebekajakob.library.service;

import com.rebekajakob.library.model.Author;
import com.rebekajakob.library.model.Book;
import com.rebekajakob.library.model.LibraryUser;
import com.rebekajakob.library.model.Reservation;
import com.rebekajakob.library.repository.AuthorRepository;
import com.rebekajakob.library.repository.BookRepository;
import com.rebekajakob.library.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class BookService {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    private LibraryUserRepository libraryUserRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, LibraryUserRepository libraryUserRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.libraryUserRepository = libraryUserRepository;
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

    public Book getBookById(String bookId){
        return bookRepository.findById(UUID.fromString(bookId)).get();
    }

    public void updateBook(String bookId,Book book){
        Book currentBook = bookRepository.findById(UUID.fromString(bookId)).get();
        if(book.getTitle() != null && !book.getTitle().equals(currentBook.getTitle())){
            currentBook.setTitle(book.getTitle());
        }
        if(book.getPages() != 0 && book.getPages()!= currentBook.getPages()){
            currentBook.setPages(book.getPages());
        }
        if(book.getDescription() != null && book.getDescription().equals(currentBook.getDescription())){
            currentBook.setDescription(book.getDescription());
        }
        bookRepository.save(currentBook);
    }

    public void reserveBook(String bookId, LibraryUser libraryUser){
        Book currentBook = bookRepository.findById(UUID.fromString(bookId)).get();
        LibraryUser currentUser = libraryUserRepository.findById(libraryUser.getId()).get();
        Reservation reservation = Reservation.builder().reservedBy(currentUser).reservationDate(LocalDateTime.now()).endDate(LocalDateTime.now().plusWeeks(1)).build();
        currentBook.getReservations().add(reservation);
        bookRepository.save(currentBook);
    }
}
