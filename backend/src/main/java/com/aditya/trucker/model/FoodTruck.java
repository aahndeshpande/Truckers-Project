package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "food_trucks")
@NoArgsConstructor
@AllArgsConstructor
public class FoodTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cuisineType;

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
    private String contactInfo;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private Double rating = 0.0;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItem> menu;

    @OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OperatingHours> operatingHours;

    @OneToMany(mappedBy = "foodTruck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToMany
    @JoinTable(
        name = "foodtruck_community",
        joinColumns = @JoinColumn(name = "foodtruck_id"),
        inverseJoinColumns = @JoinColumn(name = "community_id")
    )
    private List<Community> communities;

    @ManyToMany
    @JoinTable(
        name = "foodtruck_event",
        joinColumns = @JoinColumn(name = "foodtruck_id"),
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = LocalDateTime.now();
    }

    public void updateFoodTruck(
        String name, 
        String cuisineType, 
        String location, 
        String address, 
        String city, 
        String state, 
        String zipCode, 
        String contactInfo, 
        String imageUrl
    ) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.location = location;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.contactInfo = contactInfo;
        this.imageUrl = imageUrl;
    }

    public void addMenuItem(MenuItem item) {
        item.setFoodTruck(this);
        menu.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        menu.remove(item);
        item.setFoodTruck(null);
    }

    public void addOperatingHours(OperatingHours hours) {
        hours.setFoodTruck(this);
        operatingHours.add(hours);
    }

    public void removeOperatingHours(OperatingHours hours) {
        operatingHours.remove(hours);
        hours.setFoodTruck(null);
    }

    public void addReview(Review review) {
        review.setFoodTruck(this);
        reviews.add(review);
    }

    public void removeReview(Review review) {
        reviews.remove(review);
        review.setFoodTruck(null);
    }

    public void addCommunity(Community community) {
        community.getFoodTrucks().add(this);
        communities.add(community);
    }

    public void removeCommunity(Community community) {
        community.getFoodTrucks().remove(this);
        communities.remove(community);
    }

    public void addEvent(Event event) {
        event.getFoodTrucks().add(this);
        events.add(event);
    }

    public void removeEvent(Event event) {
        event.getFoodTrucks().remove(this);
        events.remove(event);
    }
}
