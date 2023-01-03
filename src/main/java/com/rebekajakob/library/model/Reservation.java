package com.rebekajakob.library.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
public class Reservation{
    private UUID id;
    private LocalDateTime reservationDate;
    private LocalDateTime endDate;
    private LibraryUser reservedBy;
}
