package com.aditya.trucker.repository;

import com.aditya.trucker.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Page<MenuItem> findByFoodTruck_Id(Long foodTruckId, Pageable pageable);
    
    Page<MenuItem> findByFoodTruck_Name(String foodTruckName, Pageable pageable);
    
    Page<MenuItem> findByPriceGreaterThanEqualAndPriceLessThanEqual(
        Double minPrice,
        Double maxPrice,
        Pageable pageable
    );
    
    Page<MenuItem> findByCategory(String category, Pageable pageable);
    
    Page<MenuItem> findByCreatedDateBetween(
        LocalDateTime startDate,
        LocalDateTime endDate,
        Pageable pageable
    );
}
