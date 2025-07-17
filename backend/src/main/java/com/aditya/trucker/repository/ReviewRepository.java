package com.aditya.trucker.repository;

import com.aditya.trucker.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findByFoodTruck_Id(Long foodTruckId, Pageable pageable);
    
    Page<Review> findByUserId(Long userId, Pageable pageable);
    
    double getAverageRatingByFoodTruckId(Long foodTruckId);
    
    Page<Review> findByRatingGreaterThanEqualAndDateBetween(
        Integer minRating,
        LocalDate startDate,
        LocalDate endDate,
        Pageable pageable
    );
    
    Page<Review> findByFoodTruck_Name(String foodTruckName, Pageable pageable);
    
    Page<Review> findByUser_Name(String userName, Pageable pageable);
    
    Page<Review> findByDate(LocalDate date, Pageable pageable);
}
