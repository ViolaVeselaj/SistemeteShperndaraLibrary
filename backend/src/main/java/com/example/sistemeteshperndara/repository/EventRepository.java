package com.example.sistemeteshperndara.repository;

import com.example.sistemeteshperndara.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByTenantId(Long tenantId);
}
