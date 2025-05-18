package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.dto.BookExpirationAlertDTO;
import com.example.sistemeteshperndara.model.BookExpirationAlert;
import com.example.sistemeteshperndara.service.BookExpirationAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book-alerts")
public class BookExpirationAlertController {

    @Autowired
    private BookExpirationAlertService alertService;

    @PostMapping
    public ResponseEntity<BookExpirationAlert> createAlert(@RequestBody BookExpirationAlertDTO dto) {
        BookExpirationAlert alert = alertService.createAlert(dto);
        return ResponseEntity.ok(alert);
    }
}
