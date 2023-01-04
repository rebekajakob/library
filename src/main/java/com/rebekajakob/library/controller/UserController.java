package com.rebekajakob.library.controller;

import com.rebekajakob.library.model.LibraryUser;
import com.rebekajakob.library.model.Reservation;
import com.rebekajakob.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addUser(@RequestBody LibraryUser libraryUser){
        userService.addUser(libraryUser);
    }

    @GetMapping("/{userId}")
    public LibraryUser getUserById(@PathVariable String userId){
        //TODO: What if invalid userId
        return userService.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public void updateUser(@PathVariable String userId, @RequestBody LibraryUser libraryUser){
        userService.updateUser(userId,libraryUser);
    }

    @GetMapping("/{userId}/reservations")
    public List<Reservation> getReservationsByUser(@PathVariable String userId){
        return userService.getReservationsByUser(userId);
    }


}
