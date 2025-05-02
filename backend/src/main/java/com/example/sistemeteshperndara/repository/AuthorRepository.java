package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    boolean existsByFirstNameAndLastName(String firstName, String lastName);

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}