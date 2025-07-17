package com.aditya.trucker.repository;

import com.aditya.trucker.model.EventParticipant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EventParticipantRepository extends JpaRepository<EventParticipant, Long> {
    Page<EventParticipant> findByEventId(Long eventId, Pageable pageable);
    
    Page<EventParticipant> findByUserId(Long userId, Pageable pageable);
    
    int countByEventId(Long eventId);
    
    Page<EventParticipant> findByStatus(String status, Pageable pageable);
    
    Page<EventParticipant> findByEvent_Name(String eventName, Pageable pageable);
    
    Page<EventParticipant> findByUser_Name(String userName, Pageable pageable);
    
    Page<EventParticipant> findByRegisteredDateBetween(
        LocalDateTime startDate,
        LocalDateTime endDate,
        Pageable pageable
    );
}
