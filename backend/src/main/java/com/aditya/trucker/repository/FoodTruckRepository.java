package com.aditya.trucker.repository;

import com.aditya.trucker.model.FoodTruck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck, Long> {
    FoodTruck findByName(String name);
    List<FoodTruck> findByLocation(String location);
    List<FoodTruck> findByCuisineType(String cuisineType);
    List<FoodTruck> findByOwner_Id(Long ownerId);
}
