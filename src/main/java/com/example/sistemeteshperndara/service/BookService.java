package com.example.sistemeteshperndara.service;

import org.springframework.stereotype.Service;
import com.library.model.Book;
import com.library.repository.BookRepository;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
