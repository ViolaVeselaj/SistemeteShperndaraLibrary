package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.model.Review;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.BookRepository;
import com.example.sistemeteshperndara.repository.ReviewRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CurrentUser currentUser;

    public List<Review> getReviewsForBook(Long bookId) {
        return reviewRepository.findByBookId(bookId);
    }

    public void createReview(Long bookId, int rating, String comment, User user) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Review review = new Review();
        review.setRating(rating);
        review.setComment(comment);
        review.setUser(user);
        review.setBook(book);
        review.setTenantId(user.getTenantId());

        reviewRepository.save(review);
    }
}

