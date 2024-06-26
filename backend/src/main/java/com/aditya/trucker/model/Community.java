package com.aditya.trucker.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Community {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @ManyToMany(mappedBy = "communities")
    private List<FoodTruck> foodTrucks;

    @OneToMany(mappedBy = "community")
    private List<Event> events;

    private String contactPerson;
    private String contactEmail;

    public Community(){
    }

    public Community(String name, String address, String contactPerson, String contactEmail) {
        this.name = name;
        this.address = address;
        this.contactPerson = contactPerson;
        this.contactEmail = contactEmail;
    }

    public long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public List<FoodTruck> getFoodTrucks() {
        return foodTrucks;
    }

    public void setFoodTrucks(List<FoodTruck> foodTrucks) {
        this.foodTrucks = foodTrucks;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
