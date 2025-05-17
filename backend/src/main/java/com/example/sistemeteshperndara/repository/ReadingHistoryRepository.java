package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.ReadingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingHistoryRepository extends JpaRepository<ReadingHistory, Long> {
    List<ReadingHistory> findByUserIdAndTenantId(Long userId, Long tenantId);
}
