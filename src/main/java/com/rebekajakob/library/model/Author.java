package com.rebekajakob.library.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
public class Author {
    private UUID id;
    private String name;
    private LocalDate birthday;
    private List<Book> writtenBooks;
}
