package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    List<Shelf> findByTenantId(Long tenantId);
}
