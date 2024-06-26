package com.aditya.trucker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "foodTruck_id")
    private FoodTruck foodTruck;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;


    public Schedule(){
    }

    public Schedule(FoodTruck foodTruck, Community community, LocalDateTime dateTime) {
        this.foodTruck = foodTruck;
        this.community = community;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public FoodTruck getFoodTruck() {
        return foodTruck;
    }
    
    public void setFoodTruck(FoodTruck foodTruck) {
        this.foodTruck = foodTruck;
    }
    
    public Community getCommunity() {
        return community;
    }
    
    public void setCommunity(Community community) {
        this.community = community;
    }
    
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime){
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime){
        this.endTime = endTime;
    }
}
