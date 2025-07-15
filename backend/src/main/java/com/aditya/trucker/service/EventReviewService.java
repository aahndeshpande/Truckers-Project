package com.aditya.trucker.service;

import com.aditya.trucker.model.Event;
import com.aditya.trucker.model.EventReview;
import com.aditya.trucker.model.User;
import com.aditya.trucker.repository.EventReviewRepository;
import com.aditya.trucker.repository.EventRepository;
import com.aditya.trucker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventReviewService {
    @Autowired
    private EventReviewRepository eventReviewRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public EventReview createReview(Long userId, Long eventId, int rating, String comment,
                                  boolean isRecommended, String foodTruckRating,
                                  String organizationRating, String overallExperience) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (eventReviewRepository.findByUser_IdAndEvent_Id(userId, eventId).isPresent()) {
            throw new RuntimeException("User has already reviewed this event");
        }

        EventReview review = new EventReview(user, event, rating, comment, isRecommended,
                foodTruckRating, organizationRating, overallExperience);
        return eventReviewRepository.save(review);
    }

    @Transactional
    public EventReview updateReview(Long reviewId, int rating, String comment,
                                  boolean isRecommended, String foodTruckRating,
                                  String organizationRating, String overallExperience) {
        EventReview review = eventReviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        
        review.updateReview(rating, comment, isRecommended,
                foodTruckRating, organizationRating, overallExperience);
        return eventReviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Long reviewId) {
        EventReview review = eventReviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));
        
        eventReviewRepository.delete(review);
    }

    public List<EventReview> getEventReviews(Long eventId) {
        return eventReviewRepository.findByEvent_Id(eventId);
    }

    public List<EventReview> getUserReviews(Long userId) {
        return eventReviewRepository.findByUser_Id(userId);
    }

    public Optional<EventReview> getReview(Long userId, Long eventId) {
        return eventReviewRepository.findByUser_IdAndEvent_Id(userId, eventId);
    }

    public double getAverageRating(Long eventId) {
        List<EventReview> reviews = eventReviewRepository.findByEvent_Id(eventId);
        if (reviews.isEmpty()) return 0;
        
        double sum = reviews.stream()
                .mapToInt(EventReview::getRating)
                .sum();
        
        return sum / reviews.size();
    }
}
