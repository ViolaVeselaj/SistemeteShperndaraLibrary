package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Author;
import com.example.sistemeteshperndara.model.Book;
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

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        // Kontrolli i autorizimit bëhet në SecurityConfig përmes PermissionRepository
        // Kjo metodë ekzekutohet vetëm nëse user-i ka POST:/books në authorities

        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            return ResponseEntity.badRequest().body("Author ID is required.");
        }

        Optional<Author> authorOptional = authorService.getAuthorById(book.getAuthor().getId());
        if (authorOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Author not found.");
        }

        book.setAuthor(authorOptional.get());
        book.setTenantId(1L); // ose përdor CurrentUser nëse ruan tenantId në JWT

        bookService.saveBook(book);
        return ResponseEntity.ok("Book added successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookDetails(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
}
