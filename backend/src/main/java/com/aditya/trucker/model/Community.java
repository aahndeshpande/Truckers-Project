package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "communities")
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private String contactPerson;

    @Column(nullable = false)
    private String contactEmail;

    @Column(nullable = false)
    private String contactPhone;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String websiteUrl;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String socialMediaUrl;

    @ManyToMany
    @JoinTable(
        name = "community_food_truck",
        joinColumns = @JoinColumn(name = "community_id"),
        inverseJoinColumns = @JoinColumn(name = "food_truck_id")
    )
    private List<FoodTruck> foodTrucks;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommunityMember> members;

    public Community(String name, String address, String city, String state, String zipCode,
                    String contactPerson, String contactEmail, String contactPhone,
                    String description, String websiteUrl, String imageUrl, String socialMediaUrl) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
        this.description = description;
        this.websiteUrl = websiteUrl;
        this.imageUrl = imageUrl;
        this.socialMediaUrl = socialMediaUrl;
    }

    public void addFoodTruck(FoodTruck foodTruck) {
        foodTruck.getCommunities().add(this);
        this.foodTrucks.add(foodTruck);
    }

    public void removeFoodTruck(FoodTruck foodTruck) {
        foodTruck.getCommunities().remove(this);
        this.foodTrucks.remove(foodTruck);
    }

    public void addEvent(Event event) {
        event.setCommunity(this);
        this.events.add(event);
    }

    public void removeEvent(Event event) {
        event.setCommunity(null);
        this.events.remove(event);
    }

    public void addReview(Review review) {
        review.setCommunity(this);
        this.reviews.add(review);
    }

    public void removeReview(Review review) {
        review.setCommunity(null);
        this.reviews.remove(review);
    }

    public void addMember(CommunityMember member) {
        member.setCommunity(this);
        this.members.add(member);
    }

    public void removeMember(CommunityMember member) {
        member.setCommunity(null);
        this.members.remove(member);
    }
}
