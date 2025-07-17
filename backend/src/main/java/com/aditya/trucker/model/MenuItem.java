package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "menu_items")
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false, precision = 10, scale = 2)
    private double price;

    @ManyToOne
    @JoinColumn(name = "food_truck_id")
    private FoodTruck foodTruck;

    public MenuItem(String name, String description, double price, FoodTruck foodTruck) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.foodTruck = foodTruck;
    }

    public void updateMenuItem(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }
}
