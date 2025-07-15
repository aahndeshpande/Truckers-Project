package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "events")
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

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private boolean isPublic;

    @Column(nullable = false)
    private String contactPerson;

    @Column(nullable = false)
    private String contactEmail;

    @Column(nullable = false)
    private String contactPhone;

    @Column(nullable = false)
    private String websiteUrl;

    @Column(nullable = false)
    private String socialMediaUrl;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToMany
    @JoinTable(
        name = "event_foodtruck",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "foodtruck_id")
    )
    private List<FoodTruck> foodTrucks;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventParticipant> participants;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EventReview> reviews;

    public Event(String name, String description, String location, String address, String city,
                String state, String zipCode, LocalDateTime startTime, LocalDateTime endTime,
                int capacity, String imageUrl, boolean isPublic, String contactPerson,
                String contactEmail, String contactPhone, String websiteUrl, String socialMediaUrl) {
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
    }

    public void addFoodTruck(FoodTruck foodTruck) {
        this.foodTrucks.add(foodTruck);
    }

    public void removeFoodTruck(FoodTruck foodTruck) {
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

    public void setDescription(String description){
        this.description = description;
    }

    public Community getCommunity(){
        return community;
    }

    public void setCommunity(Community community){
        this.community = community;
    }

    public List<FoodTruck> getFoodTrucks(){
        return foodTrucks;
    }

    public void setFoodTrucks(List<FoodTruck> foodTrucks){
        this.foodTrucks = foodTrucks;
    }

    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }

}
