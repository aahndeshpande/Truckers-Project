package com.aditya.trucker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aditya.trucker.model.Community;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepo extends JpaRepository<Community,Long> {
    
}
