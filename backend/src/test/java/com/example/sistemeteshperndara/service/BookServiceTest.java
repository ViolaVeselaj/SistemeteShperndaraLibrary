package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.repository.BookRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private CurrentUser currentUser;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(currentUser.getTenantIdFromToken()).thenReturn(1L);
    }

    @Test
    void getAllBooks() {
        Book book1 = new Book();
        book1.setTitle("Book 1");
        book1.setTenantId(1L);

        Book book2 = new Book();
        book2.setTitle("Book 2");
        book2.setTenantId(1L);

        when(bookRepository.findByTenantId(1L)).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.getAllBooks();

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findByTenantId(1L);
    }

    @Test
    void saveBook() {
        Book book = new Book();
        book.setTitle("New Book");

        // Përdor logjikën reale: vendos tenantId përpara ruajtjes
        book.setTenantId(1L);
        bookService.saveBook(book);

        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void getBookById() {
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Book by ID");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Book by ID", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }
}
