package com.aditya.trucker.repository;

import com.aditya.trucker.model.FoodTruck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTruckRepository extends JpaRepository<FoodTruck, Long> {
    FoodTruck findByName(String name);
    
    Page<FoodTruck> findByLocation(String location, Pageable pageable);
    
    Page<FoodTruck> findByCuisineType(String cuisineType, Pageable pageable);
    
    Page<FoodTruck> findByOwner_Id(Long ownerId, Pageable pageable);
    
    Page<FoodTruck> findByRatingGreaterThanEqual(Double minRating, Pageable pageable);
    
    Page<FoodTruck> findByCommunity_CommunityId(Long communityId, Pageable pageable);
    
    Page<FoodTruck> findByEvents_EventId(Long eventId, Pageable pageable);
    
    Page<FoodTruck> findByReviews_RatingGreaterThanEqualAndReviews_CreatedAtBetween(
        Double minRating, 
        java.time.LocalDateTime startDate, 
        java.time.LocalDateTime endDate,
        Pageable pageable
    );
}
