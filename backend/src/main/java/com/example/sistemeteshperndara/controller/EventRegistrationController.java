package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.EventRegistration;
import com.example.sistemeteshperndara.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-registrations")
public class EventRegistrationController {

    @Autowired
    private EventRegistrationService registrationService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<String> registerToEvent(@PathVariable Long eventId) {
        registrationService.registerToEvent(eventId);
        return ResponseEntity.ok("Registration successful.");
    }

    @GetMapping
    public List<EventRegistration> getAll() {
        return registrationService.getAllForTenant();
    }
}
