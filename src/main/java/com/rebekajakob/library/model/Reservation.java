package com.rebekajakob.library.model;

import java.time.LocalDateTime;

public record Reservation(LocalDateTime reservationDate, LocalDateTime endDate) {
}
