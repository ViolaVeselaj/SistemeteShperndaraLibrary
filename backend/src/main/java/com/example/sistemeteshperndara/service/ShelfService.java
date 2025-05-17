package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Shelf;
import com.example.sistemeteshperndara.repository.ShelfRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfService {

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private CurrentUser currentUser;

    public void createShelf(Shelf shelf) {
        shelf.setTenantId(currentUser.getTenantIdFromToken());
        shelfRepository.save(shelf);
    }

    public List<Shelf> getAllShelves() {
        return shelfRepository.findByTenantId(currentUser.getTenantIdFromToken());
    }

    public Shelf getShelfById(Long id) {
        return shelfRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelf not found"));
    }
}
