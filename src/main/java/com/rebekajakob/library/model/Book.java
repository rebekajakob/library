package com.rebekajakob.library.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class Book {
    private UUID id;
    private String title;
    private Author author;
    private String description;
    private int pages;
    private List<Reservation> reservations;
}
