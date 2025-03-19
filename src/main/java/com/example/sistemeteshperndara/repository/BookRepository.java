package com.example.sistemeteshperndara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.sistemeteshperndara.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

