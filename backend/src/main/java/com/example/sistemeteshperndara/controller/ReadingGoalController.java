package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.ReadingGoal;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.service.ReadingGoalService;
import com.example.sistemeteshperndara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reading-goals")
public class ReadingGoalController {

    @Autowired
    private ReadingGoalService readingGoalService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ReadingGoal addGoal(@RequestBody ReadingGoal goal) {
        User currentUser = userService.getCurrentUser();
        goal.setUser(currentUser);
        goal.setTenantId(currentUser.getTenantId());
        return readingGoalService.save(goal);
    }

    @GetMapping
    public List<ReadingGoal> getGoalsForCurrentUser() {
        User currentUser = userService.getCurrentUser();
        return readingGoalService.getUserGoals(currentUser.getId());
    }
}
