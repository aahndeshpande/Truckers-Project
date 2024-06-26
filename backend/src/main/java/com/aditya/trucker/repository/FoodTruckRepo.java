package com.aditya.trucker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aditya.trucker.model.FoodTruck;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTruckRepo extends JpaRepository<FoodTruck, Long> {
	
}
