package com.rebekajakob.library.controller;

import com.rebekajakob.library.model.LibraryUser;
import com.rebekajakob.library.model.Reservation;
import com.rebekajakob.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<LibraryUser> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody LibraryUser libraryUser){
        LibraryUser newLibraryUser = userService.addUser(libraryUser);
        if (newLibraryUser== null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Sorry, we couldn't save this user " + libraryUser);
        }
        return ResponseEntity.ok(newLibraryUser.toString());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<String> getUserById(@PathVariable String userId){
        LibraryUser libraryUser = userService.getUserById(userId);
        if(libraryUser==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please add an existing user id!");
        }
        return ResponseEntity.ok(libraryUser.toString());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUser(@PathVariable String userId, @RequestBody LibraryUser libraryUser){
        LibraryUser updatedLibraryUser = userService.updateUser(userId,libraryUser);
        if(updatedLibraryUser==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please add an existing user id!");
        }
        return ResponseEntity.ok(updatedLibraryUser.toString());
    }

    @GetMapping("/{userId}/reservation")
    public List<Reservation> getReservationsByUser(@PathVariable String userId){
        return userService.getReservationsByUser(userId);
    }

    @PutMapping ("/{userId}/reservation/{reservationId}")
    public ResponseEntity<String> returnBook(@PathVariable String userId, @PathVariable String reservationId){
        int status = userService.returnBook(userId,reservationId);
        if(status ==0){
            return ResponseEntity.ok("Book returned!");
        } else if(status == 1){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You gave an invalid reservation id!");
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This book is already returned!");
        }
    }
}
