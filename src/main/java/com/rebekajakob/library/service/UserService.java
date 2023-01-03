package com.rebekajakob.library.service;

import com.rebekajakob.library.model.LibraryUser;
import com.rebekajakob.library.repository.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
        if(libraryUser.getMembershipRenewed()!=null && !libraryUser.getMembershipRenewed().equals(currentUser.getMembershipRenewed())){
            currentUser.setMembershipRenewed(libraryUser.getMembershipRenewed());
        }
        libraryUserRepository.save(currentUser);


    }
}
