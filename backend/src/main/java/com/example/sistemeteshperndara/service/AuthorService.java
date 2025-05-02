package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Author;
import com.example.sistemeteshperndara.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public boolean existsByName(String firstName, String lastName) {
        return authorRepository.existsByFirstNameAndLastName(firstName, lastName);
    }

    public Author save(Author author) {
        return  authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(Integer id) {
        return authorRepository.findById(id);
    }

    public Optional<Author> findByName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }
}
