package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Author;
import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.security.CurrentUser;
import com.example.sistemeteshperndara.service.AuthorService;
import com.example.sistemeteshperndara.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CurrentUser currentUser;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        String role = currentUser.getCurrentRole();
        Long tenantId = currentUser.getTenantIdFromToken();

        if (!role.equals("ADMIN") && !role.equals("LIBRARIAN")) {
            return ResponseEntity.status(403).body("Access denied. Only admins and librarians can add books.");
        }

        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            return ResponseEntity.badRequest().body("Author ID is required.");
        }

        Optional<Author> authorOptional = authorService.getAuthorById(book.getAuthor().getId());
        if (authorOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Author not found.");
        }

        book.setAuthor(authorOptional.get());
        book.setTenantId(tenantId);

        bookService.saveBook(book);
        return ResponseEntity.ok("Book added successfully.");
    }
}

//base controller metodat by default tani mi thirr nkontrollera tjer
