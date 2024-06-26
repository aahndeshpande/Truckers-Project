package com.aditya.trucker.model;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rating;

    private String comment;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "food_truck_id")
    private FoodTruck foodTruck;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Review() {
        // Default constructor
    }

    public Review(Integer rating, String comment, LocalDate date, FoodTruck foodTruck, Customer customer) {
        this.rating = rating;
        this.comment = comment;
        this.date = date;
        this.foodTruck = foodTruck;
        this.customer = customer;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public FoodTruck getFoodTruck() {
        return foodTruck;
    }

    public void setFoodTruck(FoodTruck foodTruck) {
        this.foodTruck = foodTruck;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}