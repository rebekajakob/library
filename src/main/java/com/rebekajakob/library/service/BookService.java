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
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LibraryUserRepository libraryUserRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, LibraryUserRepository libraryUserRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.libraryUserRepository = libraryUserRepository;
    }

    public Book addBook(Book book){
        book.setReturned(true);
        return bookRepository.save(book);
    }

    public Book addAuthorToBook(String bookId, Author author){
        Book currentBook;
        Author currentAuthor;
        if(bookRepository.findById(UUID.fromString(bookId)).isEmpty()){
            return null;
        }
        if(authorRepository.findById(author.getId()).isEmpty()){
            return null;
        }
        currentBook =  bookRepository.findById(UUID.fromString(bookId)).get();
        currentAuthor = authorRepository.findById(author.getId()).get();
        currentBook.setAuthor(currentAuthor);
        return bookRepository.save(currentBook);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(String bookId){
        if (bookRepository.findById(UUID.fromString(bookId)).isEmpty()){
            return null;
        }
        return bookRepository.findById(UUID.fromString(bookId)).get();
    }

    public Book updateBook(String bookId,Book book){
        if(bookRepository.findById(UUID.fromString(bookId)).isEmpty()){
            return null;
        }
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
        return bookRepository.save(currentBook);
    }

    public int reserveBook(String bookId, LibraryUser libraryUser){
        if(bookRepository.findById(UUID.fromString(bookId)).isEmpty()){
            return 1;
        }
        if(libraryUserRepository.findById(libraryUser.getId()).isEmpty()){
            return 2;
        }
        Book currentBook = bookRepository.findById(UUID.fromString(bookId)).get();
        LibraryUser currentUser = libraryUserRepository.findById(libraryUser.getId()).get();
        if(!currentBook.isReturned()){
            return 3;
        }
        Reservation reservation = Reservation.builder().reservedBy(currentUser).reservationDate(LocalDateTime.now()).endDate(LocalDateTime.now().plusWeeks(1)).build();
        currentBook.getReservations().add(reservation);
        currentBook.setReturned(false);
        bookRepository.save(currentBook);
        return 0;
    }

    public List<Book> getAvailableBooks(){
        return bookRepository.getBooksByReturned(true);
    }
}
