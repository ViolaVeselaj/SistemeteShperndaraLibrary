package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.EventRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {
    List<EventRegistration> findByTenantId(Long tenantId);
}
