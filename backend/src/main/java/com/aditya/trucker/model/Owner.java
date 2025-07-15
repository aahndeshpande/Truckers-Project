package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String businessLicenseNumber;

    @Column(nullable = false)
    private String foodServiceLicenseNumber;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipCode;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FoodTruck> foodTrucks;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Community> communities;

    public Owner(String name, String email, String phoneNumber, String businessName, String businessLicenseNumber,
                 String foodServiceLicenseNumber, String address, String city, String state, String zipCode) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.businessName = businessName;
        this.businessLicenseNumber = businessLicenseNumber;
        this.foodServiceLicenseNumber = foodServiceLicenseNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    public void addFoodTruck(FoodTruck foodTruck) {
        foodTruck.setOwner(this);
        this.foodTrucks.add(foodTruck);
    }

    public void removeFoodTruck(FoodTruck foodTruck) {
        foodTruck.setOwner(null);
        this.foodTrucks.remove(foodTruck);
    }

    public void addReview(Review review) {
        review.setOwner(this);
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        review.setOwner(null);
        this.reviews.remove(review);
    }

    public void addEvent(Event event) {
        event.setOwner(this);
        this.events.add(event);
    }

    public void removeEvent(Event event) {
        event.setOwner(null);
        this.events.remove(event);
    }

    public void addCommunity(Community community) {
        community.setOwner(this);
        this.communities.add(community);
    }

    public void removeCommunity(Community community) {
        community.setOwner(null);
        this.communities.remove(community);
    }
        this.email = email;
    }

    public List<Review> getReviews(){
        return reviews;
    }

    public void setReviews(List<Review> reviews){
        this.reviews = reviews;
    }

}
