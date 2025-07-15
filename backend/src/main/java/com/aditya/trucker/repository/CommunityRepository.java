package com.aditya.trucker.repository;

import com.aditya.trucker.model.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findByName(String name);
    List<Community> findByAddress(String address);
    List<Community> findByContactEmail(String contactEmail);
    List<Community> findByFoodTrucks_Id(Long foodTruckId);
    List<Community> findByEvents_Id(Long eventId);
}
