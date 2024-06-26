package com.aditya.trucker.repository;

import org.springframework.stereotype.Repository;
import com.aditya.trucker.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ReviewRepo extends JpaRepository<Review, Long>{

}
