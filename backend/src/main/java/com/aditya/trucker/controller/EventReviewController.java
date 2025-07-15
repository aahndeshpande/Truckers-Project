package com.aditya.trucker.controller;

import com.aditya.trucker.model.EventReview;
import com.aditya.trucker.service.EventReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/event-reviews")
public class EventReviewController {
    
    @Autowired
    private EventReviewService eventReviewService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EventReview> createReview(
            @RequestParam Long userId,
            @RequestParam Long eventId,
            @RequestParam int rating,
            @RequestParam String comment,
            @RequestParam boolean isRecommended,
            @RequestParam String foodTruckRating,
            @RequestParam String organizationRating,
            @RequestParam String overallExperience) {
        EventReview review = eventReviewService.createReview(
                userId, eventId, rating, comment, isRecommended,
                foodTruckRating, organizationRating, overallExperience);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<EventReview> updateReview(
            @PathVariable Long id,
            @RequestParam int rating,
            @RequestParam String comment,
            @RequestParam boolean isRecommended,
            @RequestParam String foodTruckRating,
            @RequestParam String organizationRating,
            @RequestParam String overallExperience) {
        EventReview review = eventReviewService.updateReview(
                id, rating, comment, isRecommended,
                foodTruckRating, organizationRating, overallExperience);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
        eventReviewService.deleteReview(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<EventReview>> getEventReviews(@PathVariable Long eventId) {
        List<EventReview> reviews = eventReviewService.getEventReviews(eventId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<EventReview>> getUserReviews(@PathVariable Long userId) {
        List<EventReview> reviews = eventReviewService.getUserReviews(userId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/status/{userId}/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Optional<EventReview>> getReviewStatus(
            @PathVariable Long userId,
            @PathVariable Long eventId) {
        Optional<EventReview> review = eventReviewService.getReview(userId, eventId);
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @GetMapping("/average-rating/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Double> getAverageRating(@PathVariable Long eventId) {
        double average = eventReviewService.getAverageRating(eventId);
        return new ResponseEntity<>(average, HttpStatus.OK);
    }
}
