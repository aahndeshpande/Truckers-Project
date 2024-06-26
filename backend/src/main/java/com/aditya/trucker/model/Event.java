package com.aditya.trucker.model;

import java.time.LocalDate;
import java.util.List;
import jakarta.persistence.*;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    private LocalDate date;
    
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
    
    @ManyToMany
    @JoinTable(name = "event_foodtruck",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "foodtruck_id"))
    private List<FoodTruck> foodTrucks;
    
    // getters and setters

    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String setDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public Community geCommunity(){
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
