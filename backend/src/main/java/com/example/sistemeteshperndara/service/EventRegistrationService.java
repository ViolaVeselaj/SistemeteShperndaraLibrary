package com.example.sistemeteshperndara.service;

import com.example.sistemeteshperndara.model.Event;
import com.example.sistemeteshperndara.model.EventRegistration;
import com.example.sistemeteshperndara.model.User;
import com.example.sistemeteshperndara.repository.EventRegistrationRepository;
import com.example.sistemeteshperndara.repository.EventRepository;
import com.example.sistemeteshperndara.repository.UserRepository;
import com.example.sistemeteshperndara.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventRegistrationService {

    @Autowired
    private EventRegistrationRepository registrationRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentUser currentUser;

    public void registerToEvent(Long eventId) {
        Long userId = currentUser.getUserIdFromToken();
        Long tenantId = currentUser.getTenantIdFromToken();

        User user = userRepository.findById(userId).orElseThrow();
        Event event = eventRepository.findById(eventId).orElseThrow();

        EventRegistration registration = new EventRegistration();
        registration.setUser(user);
        registration.setEvent(event);
        registration.setTenantId(tenantId);
        registration.setStatus("CONFIRMED");

        registrationRepository.save(registration);
    }

    public List<EventRegistration> getAllForTenant() {
        return registrationRepository.findByTenantId(currentUser.getTenantIdFromToken());
    }
}
