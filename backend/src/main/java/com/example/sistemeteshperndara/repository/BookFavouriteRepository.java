package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.BookFavourite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookFavouriteRepository extends JpaRepository<BookFavourite, Long> {
    boolean existsByUserIdAndBookId(Long userId, Long bookId);
}
