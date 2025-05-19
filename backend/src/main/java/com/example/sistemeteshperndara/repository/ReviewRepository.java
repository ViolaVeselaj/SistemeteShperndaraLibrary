package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBookId(Long bookId);

    @Query("SELECT r FROM Review r")
    List<Review> findAllUnfiltered();

}

