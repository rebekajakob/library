package com.rebekajakob.library.repository;

import com.rebekajakob.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("SELECT b FROM Book b JOIN Reservation r WHERE r.id = :reservationId")
    Book getBookByReservationId(@Param("reservationId") UUID reservationId);

    List<Book> getBooksByReturned(boolean returned);


}
