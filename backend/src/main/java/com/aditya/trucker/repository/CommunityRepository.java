package com.aditya.trucker.repository;

import com.aditya.trucker.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    Page<Community> findByName(String name, Pageable pageable);
    
    Page<Community> findByAddress(String address, Pageable pageable);
    
    Page<Community> findByContactEmail(String contactEmail, Pageable pageable);
    
    Page<Community> findByFoodTrucksId(Long foodTruckId, Pageable pageable);
    
    Page<Community> findByEventsId(Long eventId, Pageable pageable);
    
    Page<Community> findByCity(String city, Pageable pageable);
    
    Page<Community> findByState(String state, Pageable pageable);
    
    Page<Community> findByZipCode(String zipCode, Pageable pageable);
    
    Page<Community> findAllByOrderByCreatedDateDesc(Pageable pageable);
    
    Page<Community> findByOwnerIdOrderByCreatedDateDesc(Long ownerId, Pageable pageable);
    
    Page<Community> findByRatingGreaterThanEqualAndCreatedDateBetween(
        Double minRating,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Pageable pageable
    );
    
    Page<Community> findByFoodTrucks_Name(String foodTruckName, Pageable pageable);
    
    Page<Community> findByEvents_Name(String eventName, Pageable pageable);
    
    Page<Community> findByMembers_User_Id(Long userId, Pageable pageable);
}
