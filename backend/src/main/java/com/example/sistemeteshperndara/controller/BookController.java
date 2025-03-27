package com.example.sistemeteshperndara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.service.BookService;

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

    @PostMapping
    public String addBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return "Libri u shtua me sukses!";
    }
}
//base controller metodat by default tani mi thirr nkontrollera tjer
