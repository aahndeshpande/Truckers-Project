package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_reviews")
public class EventReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int rating;

    @Column(nullable = false, length = 1000)
    private String comment;

    @Column(nullable = false)
    private LocalDateTime reviewDate;

    @Column(nullable = false)
    private boolean isRecommended;

    @Column(nullable = false)
    private String foodTruckRating;

    @Column(nullable = false)
    private String organizationRating;

    @Column(nullable = false)
    private String overallExperience;

    public EventReview(User user, Event event, int rating, String comment, boolean isRecommended,
                       String foodTruckRating, String organizationRating, String overallExperience) {
        this.user = user;
        this.event = event;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = LocalDateTime.now();
        this.isRecommended = isRecommended;
        this.foodTruckRating = foodTruckRating;
        this.organizationRating = organizationRating;
        this.overallExperience = overallExperience;
    }

    public void updateReview(int rating, String comment, boolean isRecommended,
                            String foodTruckRating, String organizationRating, String overallExperience) {
        this.rating = rating;
        this.comment = comment;
        this.isRecommended = isRecommended;
        this.foodTruckRating = foodTruckRating;
        this.organizationRating = organizationRating;
        this.overallExperience = overallExperience;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
