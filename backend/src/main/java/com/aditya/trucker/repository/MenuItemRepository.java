package com.aditya.trucker.repository;

import com.aditya.trucker.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByFoodTruck_Id(Long foodTruckId);
    List<MenuItem> findByFoodTruck_Name(String foodTruckName);
}
