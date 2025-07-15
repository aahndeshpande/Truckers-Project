package com.aditya.trucker.repository;

import com.aditya.trucker.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByName(String name);
    List<Event> findByDate(LocalDate date);
    List<Event> findByCommunity_Id(Long communityId);
    List<Event> findByFoodTrucks_Id(Long foodTruckId);
    List<Event> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
