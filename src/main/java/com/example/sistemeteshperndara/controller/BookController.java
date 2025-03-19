package com.example.sistemeteshperndara.controller;

import org.springframework.web.bind.annotation.*;
import com.library.model.Book;
import com.library.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping("/add")
    public String addBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return "Libri u shtua me sukses!";
    }
}
