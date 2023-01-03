package com.rebekajakob.library.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class Author {
    private String name;
    private LocalDate birthday;
    private List<Book> writtenBooks;
}
