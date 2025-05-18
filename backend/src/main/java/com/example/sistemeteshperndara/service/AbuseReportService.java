package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.dto.AbuseReportDTO;
import com.example.sistemeteshperndara.model.AbuseReport;
import com.example.sistemeteshperndara.model.Review;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.AbuseReportRepository;
import com.example.sistemeteshperndara.repository.ReviewRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AbuseReportService {

    @Autowired
    private AbuseReportRepository abuseReportRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public AbuseReport createReport(AbuseReportDTO dto) {
        User user = userRepository.findById(dto.getReportingUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Review review = reviewRepository.findById(dto.getReviewId())
                .orElseThrow(() -> new IllegalArgumentException("Review not found"));

        AbuseReport report = new AbuseReport();
        report.setReportingUser(user);
        report.setReview(review);
        report.setReason(dto.getReason());
        report.setTenantId(dto.getTenantId());
        report.setCreatedAt(java.time.Instant.now());

        return abuseReportRepository.save(report);
    }
}
