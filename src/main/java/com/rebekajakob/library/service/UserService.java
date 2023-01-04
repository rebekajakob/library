package com.rebekajakob.library.service;

import com.rebekajakob.library.model.LibraryUser;
import com.rebekajakob.library.model.Reservation;
import com.rebekajakob.library.repository.LibraryUserRepository;
import com.rebekajakob.library.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private LibraryUserRepository libraryUserRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public UserService(LibraryUserRepository libraryUserRepository, ReservationRepository reservationRepository) {
        this.libraryUserRepository = libraryUserRepository;
        this.reservationRepository = reservationRepository;
    }

    public void addUser(LibraryUser libraryUser){
        //TODO: unique email
        libraryUser.setStartOfMembership(LocalDate.now());
        libraryUserRepository.save(libraryUser);
    }

    public List<LibraryUser> getAllUsers(){
        return libraryUserRepository.findAll();
    }

    public LibraryUser getUserById(String userId){
        return libraryUserRepository.findById(UUID.fromString(userId)).get();
    }

    public void updateUser(String userId, LibraryUser libraryUser){
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
        libraryUserRepository.save(currentUser);
    }

    public List<Reservation> getReservationsByUser(String userID){
        LibraryUser currentUser = libraryUserRepository.findById(UUID.fromString(userID)).get();
        return reservationRepository.getReservationsByReservedBy(currentUser);
    }
}
