package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.ReadingGoal;
import com.example.sistemeteshperndara.repository.ReadingGoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingGoalService {

    @Autowired
    private ReadingGoalRepository readingGoalRepository;

    public ReadingGoal save(ReadingGoal goal) {
        return readingGoalRepository.save(goal);
    }

    public List<ReadingGoal> getUserGoals(Long userId) {
        return readingGoalRepository.findByUserId(userId);
    }
}
