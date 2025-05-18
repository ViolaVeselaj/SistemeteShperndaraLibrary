package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.dto.BookRequestSuggestionDTO;
import com.example.sistemeteshperndara.model.BookRequestSuggestion;
import com.example.sistemeteshperndara.service.BookRequestSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book-suggestions")
public class BookRequestSuggestionController {

    @Autowired
    private BookRequestSuggestionService suggestionService;

    @PostMapping
    public ResponseEntity<BookRequestSuggestion> suggestBook(@RequestBody BookRequestSuggestionDTO dto) {
        BookRequestSuggestion suggestion = suggestionService.createSuggestion(dto);
        return ResponseEntity.ok(suggestion);
    }
}
