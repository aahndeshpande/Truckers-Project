package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "reviews")
@EqualsAndHashCode(of = "id")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false, length = 1000)
    private String comment;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_truck_id")
    private FoodTruck foodTruck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "is_verified", nullable = false)
    private boolean isVerified = false;

    public Review(Integer rating, String comment, User user, FoodTruck foodTruck) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
        this.foodTruck = foodTruck;
        if (user instanceof Customer) {
            this.customer = (Customer) user;
        }
    }

    public void updateReview(Integer rating, String comment) {
        if (rating != null) {
            this.rating = rating;
        }
        if (comment != null && !comment.trim().isEmpty()) {
            this.comment = comment;
        }
        this.updatedAt = LocalDateTime.now();
    }
}