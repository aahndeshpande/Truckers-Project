package com.aditya.trucker.repository;

import com.aditya.trucker.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByUserId(Long userId);
    List<Owner> findByCommunityId(Long communityId);
    List<Owner> findByFoodTruckId(Long foodTruckId);
}
