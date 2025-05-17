package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.BookRequest;
import com.example.sistemeteshperndara.service.BookRequestService;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-requests")
public class BookRequestController {

    @Autowired
    private BookRequestService bookRequestService;

    @Autowired
    private CurrentUser currentUser;

    @PostMapping
    public ResponseEntity<String> requestBook(@RequestBody BookRequest bookRequest) {
        if (!currentUser.getCurrentRole().equals("USER")) {
            return ResponseEntity.status(403).body("Only users can request books.");
        }
        bookRequestService.createRequest(bookRequest);
        return ResponseEntity.ok("Book request submitted successfully.");
    }

    @GetMapping
    public List<BookRequest> getAllRequests() {
        return bookRequestService.getAllRequestsForTenant();
    }
}

