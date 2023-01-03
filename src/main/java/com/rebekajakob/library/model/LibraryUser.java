package com.rebekajakob.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LibraryUser {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    //TODO: password hash
    private String password;
    private LocalDate birthday;
    private LocalDate startOfMembership;
    private LocalDate membershipRenewed;
}
