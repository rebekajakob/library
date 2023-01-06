package com.rebekajakob.library.repository;

import com.rebekajakob.library.model.Book;
import com.rebekajakob.library.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("SELECT b FROM Book b join b.reservations as r WHERE r.id = ?1")
    Book getBookByReservationId(UUID reservationId);

    List<Book> getBooksByReturned(boolean returned);


}
