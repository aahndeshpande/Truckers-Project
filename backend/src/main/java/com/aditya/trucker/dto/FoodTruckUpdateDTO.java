package com.aditya.trucker.dto;

import com.aditya.trucker.validation.AtLeastOneNotNull;
import com.aditya.trucker.validation.UpdateGroup;
import com.aditya.trucker.validation.ValidPhoneNumber;
import com.aditya.trucker.validation.ValidState;
import com.aditya.trucker.validation.ValidZipCode;
import jakarta.validation.constraints.*;

import java.util.List;

/**
 * DTO for updating a FoodTruck entity.
 * Used for PATCH requests where only specific fields need to be updated.
 */
@AtLeastOneNotNull(
    fieldNames = {"name", "description", "cuisineType", "phoneNumber", 
                "email", "address", "city", "state", "zipCode", 
                "latitude", "longitude", "status"},
    groups = UpdateGroup.class
)
public class FoodTruckUpdateDTO {
    
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters", groups = UpdateGroup.class)
    private String name;
    
    @Size(max = 1000, message = "Description cannot exceed 1000 characters", groups = UpdateGroup.class)
    private String description;
    
    @Size(max = 50, message = "Cuisine type cannot exceed 50 characters", groups = UpdateGroup.class)
    private String cuisineType;
    
    @Size(max = 255, message = "Image URL cannot exceed 255 characters", groups = UpdateGroup.class)
    @Pattern(regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$", 
             message = "Invalid URL format", groups = UpdateGroup.class)
    private String imageUrl;
    
    @ValidPhoneNumber(message = "Invalid phone number format. Please use a valid 10-digit US phone number", 
                     groups = UpdateGroup.class)
    private String phoneNumber;
    
    @Email(message = "Invalid email format", groups = UpdateGroup.class)
    @Size(max = 100, message = "Email cannot exceed 100 characters", groups = UpdateGroup.class)
    private String email;
    
    @Size(max = 255, message = "Address cannot exceed 255 characters", groups = UpdateGroup.class)
    private String address;
    
    @Size(max = 100, message = "City cannot exceed 100 characters", groups = UpdateGroup.class)
    private String city;
    
    @ValidState(message = "Invalid US state abbreviation", groups = UpdateGroup.class)
    private String state;
    
    @ValidZipCode(message = "Invalid ZIP code format. Use 12345 or 12345-6789", groups = UpdateGroup.class)
    private String zipCode;
    
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90", groups = UpdateGroup.class)
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90", groups = UpdateGroup.class)
    private Double latitude;
    
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180", groups = UpdateGroup.class)
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180", groups = UpdateGroup.class)
    private Double longitude;
    
    @Size(max = 50, message = "Status cannot exceed 50 characters", groups = UpdateGroup.class)
    private String status;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
