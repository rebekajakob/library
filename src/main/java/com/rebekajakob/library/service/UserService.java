package com.rebekajakob.library.service;

import com.rebekajakob.library.model.LibraryUser;
import com.rebekajakob.library.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    public UserService(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }

    public void addUser(LibraryUser libraryUser){
        //TODO: unique email
        libraryUser.setStartOfMembership(LocalDate.now());
        libraryUserRepository.save(libraryUser);
    }
}
