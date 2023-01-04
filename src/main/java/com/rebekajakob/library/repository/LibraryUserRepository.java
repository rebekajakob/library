package com.rebekajakob.library.repository;

import com.rebekajakob.library.model.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, UUID> {
}
