package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.ReadingProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingProgressRepository extends JpaRepository<ReadingProgress, Long> {
    List<ReadingProgress> findByUserIdAndTenantId(Long userId, Long tenantId);
}

