package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.dto.AbuseReportDTO;
import com.example.sistemeteshperndara.model.AbuseReport;
import com.example.sistemeteshperndara.service.AbuseReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/abuse-reports")
public class AbuseReportController {

    @Autowired
    private AbuseReportService abuseReportService;

    @PostMapping
    public ResponseEntity<AbuseReport> reportReview(@RequestBody AbuseReportDTO dto) {
        AbuseReport report = abuseReportService.createReport(dto);
        return ResponseEntity.ok(report);
    }
}
