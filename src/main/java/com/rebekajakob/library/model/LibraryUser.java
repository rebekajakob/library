package com.rebekajakob.library.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
public class LibraryUser {
    private String name;
    private String email;
    //TODO: password hash
    private String password;
    private List<Book> currentlyRentedBooks;
    private LocalDate birthday;
    private LocalDate startOfMembership;
    private LocalDate membershipRenewed;
}
