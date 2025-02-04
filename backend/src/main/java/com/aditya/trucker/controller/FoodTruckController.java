package com.aditya.trucker.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aditya.trucker.model.FoodTruck;
import com.aditya.trucker.service.FoodTruckService;

@RestController
@RequestMapping("/foodtrucks")
public class FoodTruckController {

    @Autowired
    private FoodTruckService foodTruckService;

    @GetMapping
    public ResponseEntity<List<FoodTruck>> getAllFoodTrucks() {
        return ResponseEntity.ok(foodTruckService.getAllFoodTrucks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodTruck> getFoodTruckById(@PathVariable Long id) {
        return foodTruckService.getFoodTruckById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<FoodTruck> addFoodTruck(@RequestBody FoodTruck foodTruck) {
        return ResponseEntity.status(HttpStatus.CREATED).body(foodTruckService.addFoodTruck(foodTruck));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFoodTruck(@PathVariable Long id, @RequestBody FoodTruck foodTruck) {
        foodTruckService.updateFoodTruck(id, foodTruck);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFoodTruck(@PathVariable Long id) {
        foodTruckService.deleteFoodTruck(id);
        return ResponseEntity.noContent().build();
    }
}
