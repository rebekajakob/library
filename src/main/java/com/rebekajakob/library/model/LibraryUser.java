package com.rebekajakob.library.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public class LibraryUser {
    private UUID id;
    private String name;
    private String email;
    //TODO: password hash
    private String password;
    private LocalDate birthday;
    private LocalDate startOfMembership;
    private LocalDate membershipRenewed;
}
