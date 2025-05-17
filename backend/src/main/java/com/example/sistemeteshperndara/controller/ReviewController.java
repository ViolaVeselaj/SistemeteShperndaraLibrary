package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Review;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.service.ReviewService;
import com.example.sistemeteshperndara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsForBook(@PathVariable Long bookId) {
        return reviewService.getReviewsForBook(bookId);
    }

    @PostMapping("/book/{bookId}")
    public ResponseEntity<String> addReview(@PathVariable Long bookId, @RequestBody Map<String, Object> body) {
        int rating = (int) body.get("rating");
        String comment = (String) body.get("comment");

        User user = userService.getCurrentUser();
        reviewService.createReview(bookId, rating, comment, user);
        return ResponseEntity.ok("Review submitted successfully.");
    }
}

