package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Shelf;
import com.example.sistemeteshperndara.security.CurrentUser;
import com.example.sistemeteshperndara.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelves")
public class ShelfController {

    @Autowired
    private ShelfService shelfService;

    @Autowired
    private CurrentUser currentUser;

    @PostMapping
    public ResponseEntity<String> createShelf(@RequestBody Shelf shelf) {
        shelfService.createShelf(shelf);
        return ResponseEntity.ok("Shelf created successfully.");
    }

    @GetMapping
    public List<Shelf> getAll() {
        return shelfService.getAllShelves();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shelf> getShelf(@PathVariable Long id) {
        return ResponseEntity.ok(shelfService.getShelfById(id));
    }
}
