package com.rebekajakob.library.service;

import com.rebekajakob.library.model.Book;
import com.rebekajakob.library.model.LibraryUser;
import com.rebekajakob.library.model.Reservation;
import com.rebekajakob.library.repository.BookRepository;
import com.rebekajakob.library.repository.LibraryUserRepository;
import com.rebekajakob.library.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private LibraryUserRepository libraryUserRepository;
    private ReservationRepository reservationRepository;
    private BookRepository bookRepository;

    @Autowired
    public UserService(LibraryUserRepository libraryUserRepository, ReservationRepository reservationRepository, BookRepository bookRepository) {
        this.libraryUserRepository = libraryUserRepository;
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
    }

    public LibraryUser addUser(LibraryUser libraryUser){
        libraryUser.setStartOfMembership(LocalDate.now());
        return libraryUserRepository.save(libraryUser);
    }

    public List<LibraryUser> getAllUsers(){
        return libraryUserRepository.findAll();
    }

    public LibraryUser getUserById(String userId){
        if(libraryUserRepository.findById(UUID.fromString(userId)).isPresent()){
            return libraryUserRepository.findById(UUID.fromString(userId)).get();
        }
        return null;
    }

    public LibraryUser updateUser(String userId, LibraryUser libraryUser){
        if(libraryUserRepository.findById(UUID.fromString(userId)).isEmpty()){
            return null;
        }
        LibraryUser currentUser = libraryUserRepository.findById(UUID.fromString(userId)).get();
        if(libraryUser.getName() != null && !libraryUser.getName().equals(currentUser.getName())){
            currentUser.setName(libraryUser.getName());
        }
        if(libraryUser.getBirthday()!= null && !libraryUser.getBirthday().equals(currentUser.getBirthday())){
            currentUser.setBirthday(libraryUser.getBirthday());
        }
        if(libraryUser.getPassword()!= null && !libraryUser.getPassword().equals(currentUser.getPassword())){
            currentUser.setPassword(libraryUser.getPassword());
        }
        if(libraryUser.getEmail()!=null && !libraryUser.getEmail().equals(currentUser.getEmail())){
            currentUser.setEmail(libraryUser.getEmail());
        }
        libraryUserRepository.save(currentUser);
        return currentUser;
    }

    public List<Reservation> getReservationsByUser(String userID){
        LibraryUser currentUser = libraryUserRepository.findById(UUID.fromString(userID)).get();
        return reservationRepository.getReservationsByReservedBy(currentUser);
    }

    public int returnBook(String userId, String reservationId){
        LocalDateTime endDate = LocalDateTime.now();
        LibraryUser currentUser = libraryUserRepository.findById(UUID.fromString(userId)).get();
        List<Reservation> userReservations = getReservationsByUser(userId);
        Reservation currentReservation;
        boolean currentReservationValid = userReservations.stream().anyMatch(reservation -> reservation.getId().equals(UUID.fromString(reservationId)));
        if (currentReservationValid){
            currentReservation = userReservations.stream().filter(reservation -> reservation.getId().equals(UUID.fromString(reservationId))).findFirst().get();
        } else{
            return 1;
        }
        Book currentBook = bookRepository.getBookByReservationId(currentReservation.getId());
        if (!currentBook.isReturned()){
            if(Duration.between(currentReservation.getEndDate(),endDate).getNano() <0){
                currentUser.setLatelyReturnedBooks(currentUser.getLatelyReturnedBooks()+1);
            }
            currentBook.setReturned(true);
            currentReservation.setEndDate(endDate);
            reservationRepository.save(currentReservation);
            bookRepository.save(currentBook);
            return 0;
        }
        else{
            return 2;
        }
    }
}
