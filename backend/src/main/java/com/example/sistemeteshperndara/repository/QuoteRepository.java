package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
