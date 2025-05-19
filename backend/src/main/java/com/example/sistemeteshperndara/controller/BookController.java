package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Author;
import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.security.CurrentUser;
import com.example.sistemeteshperndara.service.AuthorService;
import com.example.sistemeteshperndara.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Operation(summary = "Kthen të gjithë librat e regjistruar")
    @PreAuthorize("hasAuthority('GET:/books')")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Operation(summary = "Shton një libër të ri në sistem")
    @PreAuthorize("hasAuthority('POST:/books')")
    @PostMapping
    public ResponseEntity<?> addBook(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Libri që do të shtohet") @RequestBody Book book) {

        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            return ResponseEntity.badRequest().body("Author ID is required.");
        }

        Optional<Author> authorOptional = authorService.getAuthorById(book.getAuthor().getId());
        if (authorOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("Author not found.");
        }

        book.setAuthor(authorOptional.get());
        book.setTenantId(currentUser.getTenantIdFromToken());

        bookService.saveBook(book);
        return ResponseEntity.ok("Book added successfully.");
    }

    @Operation(summary = "Kthen detajet e një libri përmes ID-së")
    @PreAuthorize("hasAuthority('GET:/books/{id}')")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookDetails(
            @Parameter(description = "ID e librit për të marrë detajet") @PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }
}
