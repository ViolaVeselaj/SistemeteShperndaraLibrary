package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Author;
import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.security.CurrentUser;
import com.example.sistemeteshperndara.service.AuthorService;
import com.example.sistemeteshperndara.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @Mock
    private CurrentUser currentUser;

    @InjectMocks
    private BookController bookController;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("Test Book");
        sampleBook.setPublishedYear(2020);
        sampleBook.setAvailableCopies(5);
        sampleBook.setTenantId(1L);

        Author author = new Author();
        author.setId(1);
        sampleBook.setAuthor(author);
    }

    @Test
    void testGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(List.of(sampleBook));

        List<Book> result = bookController.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("Test Book", result.get(0).getTitle());
        verify(bookService).getAllBooks();
    }

    @Test
    void testAddBookSuccess() {
        when(authorService.getAuthorById(1)).thenReturn(Optional.of(sampleBook.getAuthor()));
        when(currentUser.getTenantIdFromToken()).thenReturn(1L);

        ResponseEntity<?> response = bookController.addBook(sampleBook);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Book added successfully.", response.getBody());
        verify(bookService).saveBook(sampleBook);
    }

    @Test
    void testAddBookWithMissingAuthor() {
        sampleBook.setAuthor(null);

        ResponseEntity<?> response = bookController.addBook(sampleBook);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Author ID is required.", response.getBody());
    }

    @Test
    void testGetBookDetails() {
        when(bookService.getBookById(1L)).thenReturn(sampleBook);

        ResponseEntity<Book> response = bookController.getBookDetails(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Test Book", response.getBody().getTitle());
    }
}
