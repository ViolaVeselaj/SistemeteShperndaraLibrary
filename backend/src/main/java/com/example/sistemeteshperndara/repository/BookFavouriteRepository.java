package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.BookFavourite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookFavouriteRepository extends JpaRepository<BookFavourite, Long> {
    Optional<BookFavourite> findByUserIdAndBookId(Long userId, Long bookId);
}
