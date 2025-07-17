package com.aditya.trucker.repository;

import com.aditya.trucker.model.CommunityMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface CommunityMemberRepository extends JpaRepository<CommunityMember, Long> {
    Page<CommunityMember> findByCommunityId(Long communityId, Pageable pageable);
    
    Page<CommunityMember> findByUserId(Long userId, Pageable pageable);
    
    boolean existsByCommunityIdAndUserId(Long communityId, Long userId);
    
    int countByCommunityId(Long communityId);
    
    Page<CommunityMember> findByStatus(String status, Pageable pageable);
    
    Page<CommunityMember> findByCommunity_Name(String communityName, Pageable pageable);
    
    Page<CommunityMember> findByUser_Name(String userName, Pageable pageable);
    
    Page<CommunityMember> findByJoinedDateBetween(
        LocalDateTime startDate,
        LocalDateTime endDate,
        Pageable pageable
    );
}
