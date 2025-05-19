package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Review;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.service.ReviewService;
import com.example.sistemeteshperndara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    // Merr të gjitha review-t për një libër
    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsForBook(@PathVariable Long bookId) {
        return reviewService.getReviewsForBook(bookId);
    }

    // Shto review për një libër me path variable
    @PostMapping("/book/{bookId}")
    public ResponseEntity<String> addReview(@PathVariable Long bookId, @RequestBody Map<String, Object> body) {
        int rating = (int) body.get("rating");
        String comment = (String) body.get("comment");

        User user = userService.getCurrentUser();
        reviewService.createReview(bookId, rating, comment, user);
        return ResponseEntity.ok("Review submitted successfully.");
    }

    // Shto review për një libër me bookId nga body
    @PostMapping
    public ResponseEntity<String> createReview(@RequestBody Map<String, Object> body) {
        Long bookId = Long.valueOf(body.get("bookId").toString());
        int rating = Integer.parseInt(body.get("rating").toString());
        String comment = body.get("comment").toString();

        User user = userService.getCurrentUser(); // përdor userService që e ke
        reviewService.createReview(bookId, rating, comment, user);

        return ResponseEntity.status(HttpStatus.CREATED).body("Review u ruajt me sukses");
    }
}
