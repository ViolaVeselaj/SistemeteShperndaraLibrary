package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.ReadingProgress;
import com.example.sistemeteshperndara.service.ReadingProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reading-progress")
public class ReadingProgressController {

    @Autowired
    private ReadingProgressService readingProgressService;

    @PostMapping("/book/{bookId}")
    public ResponseEntity<String> saveProgress(
            @PathVariable Long bookId,
            @RequestBody Map<String, Integer> body
    ) {
        Integer currentPage = body.get("currentPage");
        Integer totalPages = body.get("totalPages");
        readingProgressService.saveProgress(bookId, currentPage, totalPages);
        return ResponseEntity.ok("Progress saved successfully.");
    }

    @GetMapping
    public List<ReadingProgress> getProgressForUser() {
        return readingProgressService.getProgressForUser();
    }
}
