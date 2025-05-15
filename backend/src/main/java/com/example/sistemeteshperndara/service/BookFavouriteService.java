package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.*;
import com.example.sistemeteshperndara.repository.BookFavouriteRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookFavouriteService {

    @Autowired
    private BookFavouriteRepository bookFavouriteRepository;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private EntityManager entityManager;

    public void addToFavourites(Long bookId) {
        Long userId = currentUser.getUserIdFromToken();
        Long tenantId = currentUser.getTenantIdFromToken();

        if (bookFavouriteRepository.existsByUserIdAndBookId(userId, bookId)) {
            throw new RuntimeException("Ky libër është tashmë në favourites.");
        }

        BookFavourite favourite = new BookFavourite();
        favourite.setUser(entityManager.getReference(User.class, userId));
        favourite.setBook(entityManager.getReference(Book.class, bookId));
        favourite.setTenantId(tenantId);

        bookFavouriteRepository.save(favourite);
    }
}
