package com.aditya.trucker.controller;

import com.aditya.trucker.model.EventParticipant;
import com.aditya.trucker.service.EventParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/event-participants")
public class EventParticipantController {
    
    @Autowired
    private EventParticipantService eventParticipantService;

    @PostMapping("/register")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EventParticipant> registerForEvent(
            @RequestParam Long userId,
            @RequestParam Long eventId,
            @RequestParam int numberOfGuests,
            @RequestParam(required = false) String specialRequests) {
        EventParticipant participant = eventParticipantService.registerForEvent(
                userId, eventId, numberOfGuests, specialRequests);
        return new ResponseEntity<>(participant, HttpStatus.CREATED);
    }

    @PostMapping("/cancel/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> cancelRegistration(@PathVariable Long id) {
        eventParticipantService.cancelRegistration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/confirm/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> confirmAttendance(@PathVariable Long id) {
        eventParticipantService.confirmAttendance(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/mark-no-show/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> markAsNoShow(@PathVariable Long id) {
        eventParticipantService.markAsNoShow(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<EventParticipant>> getEventParticipants(@PathVariable Long eventId) {
        List<EventParticipant> participants = eventParticipantService.getEventParticipants(eventId);
        return new ResponseEntity<>(participants, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<EventParticipant>> getUserEvents(@PathVariable Long userId) {
        List<EventParticipant> events = eventParticipantService.getUserEvents(userId);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/status/{userId}/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Optional<EventParticipant>> getRegistrationStatus(
            @PathVariable Long userId,
            @PathVariable Long eventId) {
        Optional<EventParticipant> participant = eventParticipantService.getRegistration(userId, eventId);
        return new ResponseEntity<>(participant, HttpStatus.OK);
    }
}
