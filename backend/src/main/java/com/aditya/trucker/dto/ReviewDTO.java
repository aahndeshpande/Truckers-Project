package com.aditya.trucker.dto;

import com.aditya.trucker.entity.Review;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Review entity.
 */
public class ReviewDTO {
    private Long id;
    private Integer rating;
    private String comment;
    private String response;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    
    private Long userId;
    private String userName;
    private String userImageUrl;
    private Long foodTruckId;
    private String foodTruckName;
    private Long eventId;
    private String eventName;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getResponse() { return response; }
    public void setResponse(String response) { this.response = response; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getUserImageUrl() { return userImageUrl; }
    public void setUserImageUrl(String userImageUrl) { this.userImageUrl = userImageUrl; }

    public Long getFoodTruckId() { return foodTruckId; }
    public void setFoodTruckId(Long foodTruckId) { this.foodTruckId = foodTruckId; }

    public String getFoodTruckName() { return foodTruckName; }
    public void setFoodTruckName(String foodTruckName) { this.foodTruckName = foodTruckName; }

    public Long getEventId() { return eventId; }
    public void setEventId(Long eventId) { this.eventId = eventId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    /**
     * Converts a Review entity to a ReviewDTO.
     *
     * @param review the Review entity to convert
     * @return the converted ReviewDTO
     */
    public static ReviewDTO fromEntity(Review review) {
        if (review == null) {
            return null;
        }

        ReviewDTO dto = new ReviewDTO();
        dto.setId(review.getId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setResponse(review.getResponse());
        dto.setCreatedAt(review.getCreatedAt());
        dto.setUpdatedAt(review.getUpdatedAt());

        if (review.getUser() != null) {
            dto.setUserId(review.getUser().getId());
            dto.setUserName(review.getUser().getUsername());
            // Assuming User has a profile image URL field
            // dto.setUserImageUrl(review.getUser().getImageUrl());
        }

        if (review.getFoodTruck() != null) {
            dto.setFoodTruckId(review.getFoodTruck().getId());
            dto.setFoodTruckName(review.getFoodTruck().getName());
        }

        if (review.getEvent() != null) {
            dto.setEventId(review.getEvent().getId());
            dto.setEventName(review.getEvent().getName());
        }

        return dto;
    }

    /**
     * Converts this DTO to a Review entity.
     *
     * @return the converted Review entity
     */
    public Review toEntity() {
        Review review = new Review();
        review.setId(this.id);
        review.setRating(this.rating);
        review.setComment(this.comment);
        review.setResponse(this.response);
        
        // Note: User, FoodTruck, and Event must be set separately as they require database lookups
        
        return review;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "id=" + id +
                ", rating=" + rating +
                ", comment='" + (comment != null ? comment.substring(0, Math.min(20, comment.length())) + "..." : "") + '\'' +
                ", userName='" + userName + '\'' +
                ", foodTruckName='" + foodTruckName + '\'' +
                '}';
    }
}
