package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Author;
import com.example.sistemeteshperndara.security.CurrentUser;
import com.example.sistemeteshperndara.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CurrentUser currentUser;

    @PostMapping("/create")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        if (authorService.existsByName(author.getFirstName(), author.getLastName())) {
            return ResponseEntity.badRequest().body("Author already exists.");
        }

        authorService.save(author);
        return ResponseEntity.ok("Author created successfully.");
    }


    @GetMapping
    public ResponseEntity<?> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable Integer id, @RequestBody Author updatedAuthor) {
        return authorService.getAuthorById(id).map(author -> {
            author.setBiography(updatedAuthor.getBiography());
            author.setBirthDate(updatedAuthor.getBirthDate());
            author.setNationality(updatedAuthor.getNationality());
            return ResponseEntity.ok(authorService.save(author));
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
