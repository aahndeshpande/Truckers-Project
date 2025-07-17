package com.aditya.trucker.controller;

import com.aditya.trucker.model.Event;
import com.aditya.trucker.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/events")
public class EventController {
    
    @Autowired
    private EventService eventService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Event> createEvent(
            @RequestParam Long ownerId,
            @RequestParam Long communityId,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String location,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String zipCode,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime,
            @RequestParam int capacity,
            @RequestParam String imageUrl,
            @RequestParam boolean isPublic,
            @RequestParam String contactPerson,
            @RequestParam String contactEmail,
            @RequestParam String contactPhone,
            @RequestParam String websiteUrl,
            @RequestParam String socialMediaUrl) {
        Event event = eventService.createEvent(
                ownerId, communityId, name, description, location, address,
                city, state, zipCode, startTime, endTime, capacity,
                imageUrl, isPublic, contactPerson, contactEmail,
                contactPhone, websiteUrl, socialMediaUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Event> updateEvent(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String location,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String zipCode,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime,
            @RequestParam int capacity,
            @RequestParam String imageUrl,
            @RequestParam boolean isPublic,
            @RequestParam String contactPerson,
            @RequestParam String contactEmail,
            @RequestParam String contactPhone,
            @RequestParam String websiteUrl,
            @RequestParam String socialMediaUrl) {
        Event event = eventService.updateEvent(
                id, name, description, location, address,
                city, state, zipCode, startTime, endTime,
                capacity, imageUrl, isPublic, contactPerson,
                contactEmail, contactPhone, websiteUrl,
                socialMediaUrl);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/community/{communityId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Event>> getEventsByCommunity(@PathVariable Long communityId) {
        List<Event> events = eventService.getEventsByCommunity(communityId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/owner/{ownerId}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<List<Event>> getEventsByOwner(@PathVariable Long ownerId) {
        List<Event> events = eventService.getEventsByOwner(ownerId);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/upcoming")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Event>> getUpcomingEvents() {
        List<Event> events = eventService.getUpcomingEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/past")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Event>> getPastEvents() {
        List<Event> events = eventService.getPastEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Event>> searchEvents(@RequestParam String query) {
        List<Event> events = eventService.searchEvents(query);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/food-truck/{foodTruckId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Event>> getEventsByFoodTruck(@PathVariable Long foodTruckId) {
        List<Event> events = eventService.getEventsByFoodTruck(foodTruckId);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/add-food-truck/{eventId}/{foodTruckId}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> addFoodTruckToEvent(
            @PathVariable Long eventId,
            @PathVariable Long foodTruckId) {
        eventService.addFoodTruckToEvent(eventId, foodTruckId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/remove-food-truck/{eventId}/{foodTruckId}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> removeFoodTruckFromEvent(
            @PathVariable Long eventId,
            @PathVariable Long foodTruckId) {
        eventService.removeFoodTruckFromEvent(eventId, foodTruckId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/full/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> isEventFull(@PathVariable Long eventId) {
        boolean isFull = eventService.isEventFull(eventId);
        return ResponseEntity.ok(isFull);
    }

    @GetMapping("/average-rating/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long eventId) {
        double average = eventService.getAverageRating(eventId);
        return ResponseEntity.ok(average);
    }

    @GetMapping("/date-range")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Event>> getEventsByDateRange(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate) {
        List<Event> events = eventService.getEventsByDateRange(startDate, endDate);
        return ResponseEntity.ok(events);
    }
}
