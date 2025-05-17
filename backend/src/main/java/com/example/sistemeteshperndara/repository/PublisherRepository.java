package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findByTenantId(Long tenantId);
}
