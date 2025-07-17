package com.aditya.trucker.repository;

import com.aditya.trucker.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Page<Event> findByName(String name, Pageable pageable);
    
    Page<Event> findByStartTime(LocalDateTime startTime, Pageable pageable);
    
    Page<Event> findByCommunityId(Long communityId, Pageable pageable);
    
    Page<Event> findByFoodTrucksId(Long foodTruckId, Pageable pageable);
    
    Page<Event> findByStartTimeBetween(
        LocalDateTime startDate, 
        LocalDateTime endDate,
        Pageable pageable
    );
    
    Page<Event> findByLocation(String location, Pageable pageable);
    
    Page<Event> findByPublicEventTrue(Pageable pageable);
    
    Page<Event> findAllByOrderByStartTimeDesc(Pageable pageable);
    
    Page<Event> findByCommunityIdOrderByStartTimeDesc(Long communityId, Pageable pageable);
    
    Page<Event> findByRatingGreaterThanEqualAndStartTimeBetween(
        Double minRating,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Pageable pageable
    );
    
    Page<Event> findByFoodTrucks_Name(String foodTruckName, Pageable pageable);
    
    Page<Event> findByCommunity_Name(String communityName, Pageable pageable);
}
