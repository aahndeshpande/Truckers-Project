package com.aditya.trucker.repository;

import com.aditya.trucker.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFoodTruck_Id(Long foodTruckId);
    List<Review> findByUserId(Long userId);
    double getAverageRatingByFoodTruckId(Long foodTruckId);
}
