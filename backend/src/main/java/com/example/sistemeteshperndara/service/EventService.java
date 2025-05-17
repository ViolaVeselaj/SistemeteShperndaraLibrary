package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Event;
import com.example.sistemeteshperndara.repository.EventRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CurrentUser currentUser;

    public List<Event> getAllEvents() {
        Long tenantId = currentUser.getTenantIdFromToken();
        return eventRepository.findByTenantId(tenantId);
    }

    public void saveEvent(Event event) {
        event.setTenantId(currentUser.getTenantIdFromToken());
        eventRepository.save(event);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }
}
