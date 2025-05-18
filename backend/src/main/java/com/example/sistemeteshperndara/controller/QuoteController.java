package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.dto.QuoteDTO;
import com.example.sistemeteshperndara.model.Quote;
import com.example.sistemeteshperndara.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quotes")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping
    public ResponseEntity<Quote> addQuote(@RequestBody QuoteDTO dto) {
        Quote quote = quoteService.createQuote(dto);
        return ResponseEntity.ok(quote);
    }
}
