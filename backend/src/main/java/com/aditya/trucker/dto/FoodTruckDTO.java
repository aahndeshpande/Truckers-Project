package com.aditya.trucker.dto;

import com.aditya.trucker.entity.FoodTruck;
import com.aditya.trucker.entity.MenuItem;
import com.aditya.trucker.entity.OperatingHours;
import com.aditya.trucker.entity.Review;
import com.aditya.trucker.validation.ValidPhoneNumber;
import com.aditya.trucker.validation.ValidState;
import com.aditya.trucker.validation.ValidZipCode;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Data Transfer Object for FoodTruck entity.
 * Used to transfer food truck data between client and server.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FoodTruckDTO {
    
    public interface CreateValidationGroup {}
    public interface UpdateValidationGroup {}
    
    @Null(groups = CreateValidationGroup.class, message = "ID must be null for create")
    @NotNull(groups = UpdateValidationGroup.class, message = "ID is required for update")
    private Long id;
    
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;
    
    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
    
    @NotBlank(message = "Cuisine type is required")
    @Size(max = 50, message = "Cuisine type cannot exceed 50 characters")
    private String cuisineType;
    
    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    @Pattern(regexp = "^(https?://)?([\\da-z.-]+)\\.([a-z.]{2,6})[/\\w .-]*/?$", 
             message = "Invalid URL format")
    private String imageUrl;
    
    @NotBlank(message = "Phone number is required")
    @ValidPhoneNumber(message = "Invalid phone number format. Please use a valid 10-digit US phone number")
    private String phoneNumber;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    private String email;
    
    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;
    
    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;
    
    @NotBlank(message = "State is required")
    @ValidState(message = "Invalid US state abbreviation")
    private String state;
    
    @NotBlank(message = "ZIP code is required")
    @ValidZipCode(message = "Invalid ZIP code format. Use 12345 or 12345-6789")
    private String zipCode;
    
    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be between -90 and 90")
    @DecimalMax(value = "90.0", message = "Latitude must be between -90 and 90")
    private Double latitude;
    
    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be between -180 and 180")
    @DecimalMax(value = "180.0", message = "Longitude must be between -180 and 180")
    private Double longitude;
    
    @DecimalMin(value = "0.0", message = "Rating must be at least 0")
    @DecimalMax(value = "5.0", message = "Rating cannot exceed 5")
    private Double rating;
    
    @Min(value = 0, message = "Review count cannot be negative")
    private Integer reviewCount;
    
    private Boolean isOpen;
    
    @Size(max = 50, message = "Status cannot exceed 50 characters")
    private String status;
    
    @NotNull(message = "Owner ID is required")
    @Positive(message = "Owner ID must be a positive number")
    private Long ownerId;
    
    private String ownerName;
    
    @Valid
    private List<@Valid MenuItemDTO> menuItems;
    
    @Valid
    private List<@Valid OperatingHoursDTO> operatingHours;
    
    @Valid
    private List<@Valid ReviewDTO> reviews;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCuisineType() { return cuisineType; }
    public void setCuisineType(String cuisineType) { this.cuisineType = cuisineType; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public Integer getReviewCount() { return reviewCount; }
    public void setReviewCount(Integer reviewCount) { this.reviewCount = reviewCount; }

    public Boolean getIsOpen() { return isOpen; }
    public void setIsOpen(Boolean open) { isOpen = open; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getOwnerId() { return ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }

    public List<MenuItemDTO> getMenuItems() { return menuItems; }
    public void setMenuItems(List<MenuItemDTO> menuItems) { this.menuItems = menuItems; }

    public List<OperatingHoursDTO> getOperatingHours() { return operatingHours; }
    public void setOperatingHours(List<OperatingHoursDTO> operatingHours) { this.operatingHours = operatingHours; }

    public List<ReviewDTO> getReviews() { return reviews; }
    public void setReviews(List<ReviewDTO> reviews) { this.reviews = reviews; }

    /**
     * Converts a FoodTruck entity to a FoodTruckDTO.
     *
     * @param foodTruck the FoodTruck entity to convert
     * @return the converted FoodTruckDTO
     */
    public static FoodTruckDTO fromEntity(FoodTruck foodTruck) {
        if (foodTruck == null) {
            return null;
        }

        FoodTruckDTO dto = new FoodTruckDTO();
        dto.setId(foodTruck.getId());
        dto.setName(foodTruck.getName());
        dto.setDescription(foodTruck.getDescription());
        dto.setCuisineType(foodTruck.getCuisineType());
        dto.setImageUrl(foodTruck.getImageUrl());
        dto.setPhoneNumber(foodTruck.getPhoneNumber());
        dto.setEmail(foodTruck.getEmail());
        dto.setAddress(foodTruck.getAddress());
        dto.setCity(foodTruck.getCity());
        dto.setState(foodTruck.getState());
        dto.setZipCode(foodTruck.getZipCode());
        dto.setLatitude(foodTruck.getLatitude());
        dto.setLongitude(foodTruck.getLongitude());
        dto.setRating(foodTruck.getRating());
        dto.setReviewCount(foodTruck.getReviewCount());
        dto.setIsOpen(foodTruck.isOpen());
        dto.setStatus(foodTruck.getStatus());

        if (foodTruck.getOwner() != null) {
            dto.setOwnerId(foodTruck.getOwner().getId());
            dto.setOwnerName(foodTruck.getOwner().getBusinessName());
        }

        // Convert related entities if they are loaded
        if (foodTruck.getMenuItems() != null) {
            dto.setMenuItems(foodTruck.getMenuItems().stream()
                    .<com.aditya.trucker.dto.MenuItemDTO>map(MenuItemDTO::fromEntity)
                    .collect(Collectors.toList()));
        }

        if (foodTruck.getOperatingHours() != null) {
            dto.setOperatingHours(foodTruck.getOperatingHours().stream()
                    .<com.aditya.trucker.dto.OperatingHoursDTO>map(OperatingHoursDTO::fromEntity)
                    .collect(Collectors.toList()));
        }

        if (foodTruck.getReviews() != null) {
            dto.setReviews(foodTruck.getReviews().stream()
                    .<com.aditya.trucker.dto.ReviewDTO>map(ReviewDTO::fromEntity)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    /**
     * Converts this DTO to a FoodTruck entity.
     *
     * @return the converted FoodTruck entity
     */
    public FoodTruck toEntity() {
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setId(this.id);
        foodTruck.setName(this.name);
        foodTruck.setDescription(this.description);
        foodTruck.setCuisineType(this.cuisineType);
        foodTruck.setImageUrl(this.imageUrl);
        foodTruck.setPhoneNumber(this.phoneNumber);
        foodTruck.setEmail(this.email);
        foodTruck.setAddress(this.address);
        foodTruck.setCity(this.city);
        foodTruck.setState(this.state);
        foodTruck.setZipCode(this.zipCode);
        foodTruck.setLatitude(this.latitude);
        foodTruck.setLongitude(this.longitude);
        foodTruck.setRating(this.rating);
        foodTruck.setReviewCount(this.reviewCount);
        foodTruck.setIsOpen(this.isOpen);
        foodTruck.setStatus(this.status);

        // Note: Owner must be set separately as it requires a database lookup
        
        if (this.menuItems != null) {
            foodTruck.setMenuItems(this.menuItems.stream()
                    .<com.aditya.trucker.entity.MenuItem>map(MenuItemDTO::toEntity)
                    .collect(Collectors.toList()));
        }

        if (this.operatingHours != null) {
            foodTruck.setOperatingHours(this.operatingHours.stream()
                    .<com.aditya.trucker.entity.OperatingHours>map(OperatingHoursDTO::toEntity)
                    .collect(Collectors.toList()));
        }

        return foodTruck;
    }

    @Override
    public String toString() {
        return "FoodTruckDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cuisineType='" + cuisineType + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", rating=" + rating +
                '}';
    }
}
