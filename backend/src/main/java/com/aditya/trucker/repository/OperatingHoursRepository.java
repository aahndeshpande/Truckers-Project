package com.aditya.trucker.repository;

import com.aditya.trucker.model.OperatingHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OperatingHoursRepository extends JpaRepository<OperatingHours, Long> {
    List<OperatingHours> findByFoodTruck_Id(Long foodTruckId);
    List<OperatingHours> findByFoodTruck_Name(String foodTruckName);
    List<OperatingHours> findByDayOfWeek(String dayOfWeek);
}
