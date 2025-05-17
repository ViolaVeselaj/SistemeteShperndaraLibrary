package com.example.sistemeteshperndara.controller;

import com.example.sistemeteshperndara.model.Event;
import com.example.sistemeteshperndara.security.CurrentUser;
import com.example.sistemeteshperndara.service.EventService;
import com.example.sistemeteshperndara.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private CurrentUser currentUser;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event event) {
        String role = currentUser.getCurrentRole();
        if (!role.equals("ADMIN") && !role.equals("LIBRARIAN")) {
            return ResponseEntity.status(403).body("Only admins or librarians can create events.");
        }

        event.setOrganizer(userService.getCurrentUser());
        eventService.saveEvent(event);
        return ResponseEntity.ok("Event created successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }
}
