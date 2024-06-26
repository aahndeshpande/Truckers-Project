package com.aditya.trucker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class FoodTruck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cuisineType;
    private String location;
    private List<MenuItem> menu;
    private List<OperatingHours> operatingHours;
    private String contactInfo;
    private String imageUrl;

    @OneToMany
    @JoinColumn(name = "review_id")
    private List<Review> reviews;
    
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    // Default constructor
    public FoodTruck() {
    }

    // Constructor with parameters
    public FoodTruck(String name, String cuisineType, String location, List<MenuItem> menu, List<OperatingHours> operatingHours, String contactInfo, String imageUrl) {
        this.name = name;
        this.cuisineType = cuisineType;
        this.location = location;
        this.menu = menu;
        this.operatingHours = operatingHours;
        this.contactInfo = contactInfo;
        this.imageUrl = imageUrl;
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for cuisineType
    public String getCuisineType() {
        return cuisineType;
    }

    // Setter for cuisineType
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Setter for location
    public void setLocation(String location) {
        this.location = location;
    }

    // Getter for menu
    public List<MenuItem> getMenu() {
        return menu;
    }

    // Setter for menu
    public void setMenu(List<MenuItem> menu) {
        this.menu = menu;
    }

    public List<OperatingHours> getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(List<OperatingHours> operatingHours){
        this.operatingHours = operatingHours;
    }

    public String getContactInfo(){
        return contactInfo;
    }

    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }

    public String getImageUrl(){
        return imageUrl;
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }
}
