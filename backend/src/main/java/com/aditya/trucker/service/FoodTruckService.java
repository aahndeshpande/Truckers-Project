package com.aditya.trucker.service;

import com.aditya.trucker.model.FoodTruck;
import com.aditya.trucker.repository.FoodTruckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodTruckService {
    @Autowired
    private FoodTruckRepo foodTruckRepo;

    public List<FoodTruck> getAllFoodTrucks() {
        return foodTruckRepo.findAll();
    }

    public Optional<FoodTruck> getFoodTruckById(Long id) {
        return foodTruckRepo.findById(id);
    }

    public FoodTruck addFoodTruck(FoodTruck foodTruck) {
        return foodTruckRepo.save(foodTruck);
    }

    public void deleteFoodTruck(Long id) {
        foodTruckRepo.deleteById(id);
    }

    public void updateFoodTruck(Long id, FoodTruck updatedFoodTruck) {
        foodTruckRepo.findById(id).map(foodTruck -> {
            foodTruck.setName(updatedFoodTruck.getName());
            foodTruck.setLocation(updatedFoodTruck.getLocation());
            foodTruck.setContactInfo(updatedFoodTruck.getContactInfo());
            foodTruck.setCuisineType(updatedFoodTruck.getCuisineType());
            foodTruck.setMenu(updatedFoodTruck.getMenu());
            foodTruck.setOperatingHours(updatedFoodTruck.getOperatingHours());
            foodTruck.setImageUrl(updatedFoodTruck.getImageUrl());
            return foodTruckRepo.save(foodTruck);
        }).orElseGet(() -> {
            updatedFoodTruck.setId(id);
            return foodTruckRepo.save(updatedFoodTruck);
        });
    }
}