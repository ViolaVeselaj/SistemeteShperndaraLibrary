package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.service.BookFavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favourites")
public class BookFavouriteController {

    @Autowired
    private BookFavouriteService bookFavouriteService;

    @PostMapping("/add")
    public ResponseEntity<String> addToFavourites(@RequestParam Long bookId) {
        bookFavouriteService.addToFavourites(bookId);
        return ResponseEntity.ok("Libri u shtua në favourites!");
    }
}
