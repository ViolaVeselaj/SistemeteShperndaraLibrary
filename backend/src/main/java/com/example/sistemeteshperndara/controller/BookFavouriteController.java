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

    @PostMapping("/toggle")
    public ResponseEntity<String> toggle(@RequestParam Long bookId) {
        String result = bookFavouriteService.toggleFavourite(bookId);
        return ResponseEntity.ok(result); // "ADDED" ose "REMOVED"
    }

    @GetMapping("/check")
    public ResponseEntity<Boolean> check(@RequestParam Long bookId) {
        boolean isFav = bookFavouriteService.isFavourite(bookId);
        return ResponseEntity.ok(isFav);
    }
}
