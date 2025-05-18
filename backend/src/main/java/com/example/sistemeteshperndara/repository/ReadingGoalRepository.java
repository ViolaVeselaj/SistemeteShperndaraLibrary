package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.ReadingGoal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadingGoalRepository extends JpaRepository<ReadingGoal, Long> {
    List<ReadingGoal> findByUserId(Long userId);
}
