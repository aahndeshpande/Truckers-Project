package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a food truck owner in the system.
 * Extends the base User class with owner-specific fields and relationships.
 */
@Entity
@Table(name = "owners")
@PrimaryKeyJoinColumn(name = "user_id")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@AttributeOverrides({
    @AttributeOverride(name = "username", column = @Column(name = "username", nullable = false, length = 50)),
    @AttributeOverride(name = "password", column = @Column(name = "password_hash", nullable = false, length = 255)),
    @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false, length = 100))
})
public class Owner extends User {
    
    @Column(name = "business_name", nullable = false, length = 100)
    private String businessName;

    @Column(name = "business_license_number", nullable = false, unique = true, length = 50)
    private String businessLicenseNumber;

    @Column(name = "food_service_license_number", nullable = false, unique = true, length = 50)
    private String foodServiceLicenseNumber;

    @OneToMany(mappedBy = "owner", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<FoodTruck> foodTrucks = new ArrayList<>();

    @OneToMany(mappedBy = "organizer", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Event> organizedEvents = new ArrayList<>();

    /**
     * Creates a new Owner instance.
     *
     * @param username The username for the owner
     * @param email The email address of the owner
     * @param password The hashed password
     * @param phoneNumber The contact phone number
     * @param address The street address
     * @param city The city
     * @param state The state/province
     * @param zipCode The postal/zip code
     * @param businessName The name of the business
     * @param businessLicenseNumber The business license number
     * @param foodServiceLicenseNumber The food service license number
     */
    public Owner(String username, String email, String password, String phoneNumber, 
                String address, String city, String state, String zipCode,
                String businessName, String businessLicenseNumber, String foodServiceLicenseNumber) {
        super(username, password, email, UserRole.OWNER);
        this.setPhoneNumber(phoneNumber);
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setZipCode(zipCode);
        this.businessName = businessName;
        this.businessLicenseNumber = businessLicenseNumber;
        this.foodServiceLicenseNumber = foodServiceLicenseNumber;
    }

    /**
     * Adds a food truck to this owner's fleet.
     *
     * @param foodTruck the food truck to add
     * @throws IllegalArgumentException if foodTruck is null
     */
    public void addFoodTruck(FoodTruck foodTruck) {
        if (foodTruck == null) {
            throw new IllegalArgumentException("Food truck cannot be null");
        }
        if (!this.foodTrucks.contains(foodTruck)) {
            this.foodTrucks.add(foodTruck);
            foodTruck.setOwner(this);
        }
    }

    /**
     * Removes a food truck from this owner's fleet.
     *
     * @param foodTruck the food truck to remove
     * @return true if the food truck was removed, false otherwise
     */
    public boolean removeFoodTruck(FoodTruck foodTruck) {
        if (foodTruck != null && this.foodTrucks.remove(foodTruck)) {
            foodTruck.setOwner(null);
            return true;
        }
        return false;
    }

    /**
     * Adds an organized event to this owner's list of events.
     *
     * @param event the event to add
     * @throws IllegalArgumentException if event is null
     */
    public void addOrganizedEvent(Event event) {
        if (event == null) {
            throw new IllegalArgumentException("Event cannot be null");
        }
        if (!this.organizedEvents.contains(event)) {
            this.organizedEvents.add(event);
            event.setOrganizer(this);
        }
    }

    /**
     * Removes an organized event from this owner's list of events.
     *
     * @param event the event to remove
     * @return true if the event was removed, false otherwise
     */
    public boolean removeOrganizedEvent(Event event) {
        if (event != null && this.organizedEvents.remove(event)) {
            event.setOrganizer(null);
            return true;
        }
        return false;
    }

    /**
     * Gets the number of food trucks owned by this owner.
     *
     * @return the number of food trucks
     */
    public int getFoodTruckCount() {
        return this.foodTrucks.size();
    }

    /**
     * Gets the number of events organized by this owner.
     *
     * @return the number of organized events
     */
    public int getOrganizedEventCount() {
        return this.organizedEvents.size();
    }

    /**
     * Checks if this owner has any active food trucks.
     *
     * @return true if the owner has at least one food truck, false otherwise
     */
    public boolean hasFoodTrucks() {
        return !this.foodTrucks.isEmpty();
    }

    /**
     * Checks if this owner has any upcoming events.
     *
     * @return true if the owner has at least one upcoming event, false otherwise
     */
    public boolean hasUpcomingEvents() {
        return this.organizedEvents.stream()
                .anyMatch(event -> event.getStartTime().isAfter(java.time.LocalDateTime.now()));
    }
}
