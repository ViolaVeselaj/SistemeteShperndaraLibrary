package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Notification;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.NotificationRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private CurrentUser currentUser;

    public List<Notification> getUserNotifications() {
        Long userId = currentUser.getUserIdFromToken();
        Long tenantId = currentUser.getTenantIdFromToken();
        return notificationRepository.findByRecipientIdAndTenantId(userId, tenantId);
    }

    public void sendNotification(User recipient, String title, String message) {
        Notification notification = new Notification();
        notification.setRecipient(recipient);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setTenantId(recipient.getTenantId());
        notificationRepository.save(notification);
    }

    public void markAsRead(Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }
}
