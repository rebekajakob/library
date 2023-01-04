package com.rebekajakob.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    @ManyToOne
    private Author author;
    private String description;
    private int pages;
    @OneToMany(orphanRemoval = true, cascade = {CascadeType.ALL})
    private List<Reservation> reservations;
    private boolean returned;
}
