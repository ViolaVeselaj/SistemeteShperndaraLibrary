package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.ReadingHistory;
import com.example.sistemeteshperndara.service.ReadingHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reading-history")
public class ReadingHistoryController {

    @Autowired
    private ReadingHistoryService historyService;

    @PostMapping("/book/{bookId}")
    public ResponseEntity<String> addHistory(
            @PathVariable Long bookId,
            @RequestBody Map<String, String> body
    ) {
        Instant start = Instant.parse(body.get("startDate"));
        Instant end = Instant.parse(body.get("endDate"));
        String status = body.get("status");
        historyService.addHistory(bookId, start, end, status);
        return ResponseEntity.ok("Reading history recorded.");
    }

    @GetMapping
    public List<ReadingHistory> getUserHistory() {
        return historyService.getUserHistory();
    }
}
