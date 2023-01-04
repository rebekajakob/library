package com.rebekajakob.library.repository;

import com.rebekajakob.library.model.LibraryUser;
import com.rebekajakob.library.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
    List<Reservation> getReservationsByReservedBy(LibraryUser libraryUser);
}
