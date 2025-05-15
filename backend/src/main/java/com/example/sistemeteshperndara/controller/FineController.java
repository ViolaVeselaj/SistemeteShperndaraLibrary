package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Fine;
import com.example.sistemeteshperndara.repository.FineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/fines")
public class FineController {

    @Autowired
    private FineRepository fineRepository;

    @GetMapping("/user/{userId}")
    public List<Fine> getFinesForUser(@PathVariable Long userId) {
        return fineRepository.findAll().stream()
                .filter(f -> f.getUser().getId().equals(userId))
                .toList();
    }

    @PostMapping("/{fineId}/pay")
    public ResponseEntity<String> payFine(@PathVariable Integer fineId) {
        Fine fine = fineRepository.findById(fineId)
                .orElseThrow(() -> new RuntimeException("Fine not found"));
        fine.setPaid(true);
        fineRepository.save(fine);
        return ResponseEntity.ok("Fine marked as paid");
    }
}

