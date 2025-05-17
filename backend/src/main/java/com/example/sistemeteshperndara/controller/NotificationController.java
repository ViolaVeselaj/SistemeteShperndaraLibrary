package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Notification;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.service.NotificationService;
import com.example.sistemeteshperndara.service.UserService;
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

    @GetMapping
    public List<Notification> getMyNotifications() {
        return notificationService.getUserNotifications();
    }

    @PutMapping("/{id}/mark-read")
    public ResponseEntity<String> markNotificationAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok("Notification marked as read");
    }

    // Test endpoint për të dërguar një njoftim manualisht
    @PostMapping("/test")
    public ResponseEntity<String> sendTestNotification() {
        User user = userService.getCurrentUser();
        notificationService.sendNotification(user, "Test Notification", "Ky është një njoftim testues.");
        return ResponseEntity.ok("Notification sent");
    }
}
