package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.BookRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRequestRepository extends JpaRepository<BookRequest, Long> {
    List<BookRequest> findByTenantId(Long tenantId);
}

