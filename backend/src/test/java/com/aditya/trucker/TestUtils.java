package com.aditya.trucker;

import com.aditya.trucker.entity.FoodTruck;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static FoodTruck createTestFoodTruck() {
        FoodTruck foodTruck = new FoodTruck();
        foodTruck.setName("Test Food Truck");
        foodTruck.setDescription("A test food truck");
        foodTruck.setCuisineType("Test Cuisine");
        foodTruck.setImageUrl("http://example.com/image.jpg");
        return foodTruck;
    }

    public static List<FoodTruck> createTestFoodTrucks(int count) {
        List<FoodTruck> foodTrucks = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            FoodTruck foodTruck = new FoodTruck();
            foodTruck.setName("Food Truck " + (i + 1));
            foodTruck.setDescription("Description " + (i + 1));
            foodTruck.setCuisineType("Cuisine " + (i % 3 + 1));
            foodTrucks.add(foodTruck);
        }
        return foodTrucks;
    }

    public static String asJsonString(final Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
