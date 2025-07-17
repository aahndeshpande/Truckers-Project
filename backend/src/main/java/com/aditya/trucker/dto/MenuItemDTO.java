package com.aditya.trucker.dto;

import com.aditya.trucker.entity.MenuItem;

import java.math.BigDecimal;

/**
 * Data Transfer Object for MenuItem entity.
 */
public class MenuItemDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String imageUrl;
    private boolean isVegetarian;
    private boolean isVegan;
    private boolean isGlutenFree;
    private boolean isSpicy;
    private Long foodTruckId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public boolean isVegetarian() { return isVegetarian; }
    public void setVegetarian(boolean vegetarian) { isVegetarian = vegetarian; }

    public boolean isVegan() { return isVegan; }
    public void setVegan(boolean vegan) { isVegan = vegan; }

    public boolean isGlutenFree() { return isGlutenFree; }
    public void setGlutenFree(boolean glutenFree) { isGlutenFree = glutenFree; }

    public boolean isSpicy() { return isSpicy; }
    public void setSpicy(boolean spicy) { isSpicy = spicy; }

    public Long getFoodTruckId() { return foodTruckId; }
    public void setFoodTruckId(Long foodTruckId) { this.foodTruckId = foodTruckId; }

    /**
     * Converts a MenuItem entity to a MenuItemDTO.
     *
     * @param menuItem the MenuItem entity to convert
     * @return the converted MenuItemDTO
     */
    public static MenuItemDTO fromEntity(MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }

        MenuItemDTO dto = new MenuItemDTO();
        dto.setId(menuItem.getId());
        dto.setName(menuItem.getName());
        dto.setDescription(menuItem.getDescription());
        dto.setPrice(menuItem.getPrice());
        dto.setCategory(menuItem.getCategory());
        dto.setImageUrl(menuItem.getImageUrl());
        dto.setVegetarian(menuItem.isVegetarian());
        dto.setVegan(menuItem.isVegan());
        dto.setGlutenFree(menuItem.isGlutenFree());
        dto.setSpicy(menuItem.isSpicy());

        if (menuItem.getFoodTruck() != null) {
            dto.setFoodTruckId(menuItem.getFoodTruck().getId());
        }

        return dto;
    }

    /**
     * Converts this DTO to a MenuItem entity.
     *
     * @return the converted MenuItem entity
     */
    public MenuItem toEntity() {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(this.id);
        menuItem.setName(this.name);
        menuItem.setDescription(this.description);
        menuItem.setPrice(this.price);
        menuItem.setCategory(this.category);
        menuItem.setImageUrl(this.imageUrl);
        menuItem.setVegetarian(this.isVegetarian);
        menuItem.setVegan(this.isVegan);
        menuItem.setGlutenFree(this.isGlutenFree);
        menuItem.setSpicy(this.isSpicy);
        
        // Note: FoodTruck must be set separately as it requires a database lookup
        
        return menuItem;
    }

    @Override
    public String toString() {
        return "MenuItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
