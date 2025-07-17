package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "events")
@EqualsAndHashCode(of = "id")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipCode;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int capacity;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "is_public", nullable = false)
    private boolean isPublic = true;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "website_url")
    private String websiteUrl;

    @Column(name = "social_media_url")
    private String socialMediaUrl;

    private Double latitude;

    private Double longitude;

    @Column(name = "average_rating", columnDefinition = "double default 0.0")
    private double averageRating = 0.0;

    @Column(name = "participant_count", columnDefinition = "int default 0")
    private int participantCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizer_id", nullable = false)
    private User organizer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToMany
    @JoinTable(
        name = "event_foodtruck",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "foodtruck_id")
    )
    private List<FoodTruck> foodTrucks = new ArrayList<>();

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EventParticipant> participants = new ArrayList<>();
    
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<EventReview> reviews = new ArrayList<>();

    public Event(String name, String description, String location, String address, String city,
                String state, String zipCode, LocalDateTime startTime, LocalDateTime endTime,
                int capacity, String imageUrl, boolean isPublic, String contactPerson,
                String contactEmail, String contactPhone, String websiteUrl, String socialMediaUrl,
                double latitude, double longitude, double averageRating, int participantCount) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.capacity = capacity;
        this.imageUrl = imageUrl;
        this.isPublic = isPublic;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.websiteUrl = websiteUrl;
        this.socialMediaUrl = socialMediaUrl;
        this.latitude = latitude;
        this.longitude = longitude;
        this.averageRating = averageRating;
        this.participantCount = participantCount;
    }

    public void addFoodTruck(FoodTruck foodTruck) {
        foodTruck.getEvents().add(this);
        this.foodTrucks.add(foodTruck);
    }

    public void removeFoodTruck(FoodTruck foodTruck) {
        foodTruck.getEvents().remove(this);
        this.foodTrucks.remove(foodTruck);
    }

    public void addParticipant(EventParticipant participant) {
        participant.setEvent(this);
        this.participants.add(participant);
    }

    public void removeParticipant(EventParticipant participant) {
        participant.setEvent(null);
        this.participants.remove(participant);
    }

    public void addReview(EventReview review) {
        review.setEvent(this);
        this.reviews.add(review);
    }

    public void removeReview(EventReview review) {
        review.setEvent(null);
        this.reviews.remove(review);
    }
}
