package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sistemeteshperndara.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll(); //filter(tenant_id=this.user.getTenantId());
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
