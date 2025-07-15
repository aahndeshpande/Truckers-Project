package com.aditya.trucker.repository;

import com.aditya.trucker.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByFoodTruck_Id(Long foodTruckId);
    List<Schedule> findByFoodTruck_Name(String foodTruckName);
    List<Schedule> findByDate(String date);
}
