package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipientIdAndTenantId(Long recipientId, Long tenantId);
}
