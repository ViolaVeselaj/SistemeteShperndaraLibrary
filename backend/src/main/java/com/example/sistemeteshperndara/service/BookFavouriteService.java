package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Book;
import com.example.sistemeteshperndara.model.BookFavourite;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.BookFavouriteRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BookFavouriteService {

    @Autowired
    private BookFavouriteRepository bookFavouriteRepository;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private EntityManager entityManager;

    public String toggleFavourite(Long bookId) {
        Long userId = currentUser.getUserIdFromToken();
        Long tenantId = currentUser.getTenantIdFromToken();

        Optional<BookFavourite> existing = bookFavouriteRepository.findByUserIdAndBookId(userId, bookId);

        if (existing.isPresent()) {
            bookFavouriteRepository.delete(existing.get());
            return "REMOVED";
        }

        BookFavourite favourite = new BookFavourite();
        favourite.setUser(entityManager.getReference(User.class, userId));
        favourite.setBook(entityManager.getReference(Book.class, bookId));
        favourite.setTenantId(tenantId);

        bookFavouriteRepository.save(favourite);
        return "ADDED";
    }

    public boolean isFavourite(Long bookId) {
        Long userId = currentUser.getUserIdFromToken();
        return bookFavouriteRepository.findByUserIdAndBookId(userId, bookId).isPresent();
    }
}
