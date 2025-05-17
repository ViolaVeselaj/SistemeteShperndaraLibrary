package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Publisher;
import com.example.sistemeteshperndara.service.PublisherService;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @Autowired
    private CurrentUser currentUser;

    @GetMapping
    public List<Publisher> getAll() {
        return publisherService.getAll();
    }

    @PostMapping
    public ResponseEntity<String> add(@RequestBody Publisher publisher) {
        String role = currentUser.getCurrentRole();
        if (!role.equals("ADMIN") && !role.equals("LIBRARIAN")) {
            return ResponseEntity.status(403).body("Access denied.");
        }
        publisherService.save(publisher);
        return ResponseEntity.ok("Publisher created.");
    }
}
