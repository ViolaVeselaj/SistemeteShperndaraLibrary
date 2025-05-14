package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistemeteshperndara.repository.BookRepository;
import com.example.sistemeteshperndara.security.CurrentUser;


import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CurrentUser currentUser;

    public List<Book> getAllBooks() {
        Long tenantId = currentUser.getTenantIdFromToken();
        return bookRepository.findByTenantId(tenantId);
    }

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

