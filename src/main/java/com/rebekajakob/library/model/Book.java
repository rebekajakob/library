package com.rebekajakob.library.model;

import lombok.Builder;

import java.util.List;

@Builder
public class Book {
    private String title;
    private Author author;
    private String description;
    private int pages;
    private List<Reservation> reservations;
}
