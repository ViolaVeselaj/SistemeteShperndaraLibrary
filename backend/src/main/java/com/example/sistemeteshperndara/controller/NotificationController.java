package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Notification;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.service.NotificationService;
import com.example.sistemeteshperndara.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Kthen të gjitha njoftimet e përdoruesit të kyçur")
    @GetMapping
    public List<Notification> getMyNotifications() {
        return notificationService.getUserNotifications();
    }

    @Operation(summary = "Shënon një njoftim si të lexuar")
    @PutMapping("/{id}/mark-read")
    public ResponseEntity<String> markNotificationAsRead(
            @Parameter(description = "ID e njoftimit që do të shënohet si i lexuar")
            @PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok("Notification marked as read");
    }

    @Operation(summary = "Dërgon një njoftim testues për përdoruesin e kyçur")
    @PostMapping("/test")
    public ResponseEntity<String> sendTestNotification() {
        User user = userService.getCurrentUser();
        notificationService.sendNotification(user, "Test Notification", "Ky është një njoftim testues.");
        return ResponseEntity.ok("Notification sent");
    }
}
