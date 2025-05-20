package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.repository.BookRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CurrentUser currentUser;

    @Cacheable("books")
    public List<Book> getAllBooks() {
        Long tenantId = currentUser.getTenantIdFromToken();
        System.out.println("Leximi i librave nga databaza për tenant: " + tenantId);
        return bookRepository.findByTenantId(tenantId);
    }

    @CacheEvict(value = "books", allEntries = true)
    public void saveBook(Book book) {
        Long tenantId = currentUser.getTenantIdFromToken();
        book.setTenantId(tenantId);
        bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libri nuk u gjet me ID: " + id));
    }
}
