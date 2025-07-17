package com.aditya.trucker.repository;

import com.aditya.trucker.model.EventReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface EventReviewRepository extends JpaRepository<EventReview, Long> {
    Page<EventReview> findByEventId(Long eventId, Pageable pageable);
    
    Page<EventReview> findByUserId(Long userId, Pageable pageable);
    
    double avgRatingByEventId(Long eventId);
    
    Page<EventReview> findByRatingGreaterThanEqualAndReviewDateBetween(
        Integer minRating,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Pageable pageable
    );
    
    Page<EventReview> findByEvent_Name(String eventName, Pageable pageable);
    
    Page<EventReview> findByUser_Name(String userName, Pageable pageable);
    
    Page<EventReview> findByReviewDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);
}
