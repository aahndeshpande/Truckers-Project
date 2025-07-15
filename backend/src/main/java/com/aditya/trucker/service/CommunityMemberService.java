package com.aditya.trucker.service;

import com.aditya.trucker.model.CommunityMember;
import com.aditya.trucker.model.Community;
import com.aditya.trucker.model.User;
import com.aditya.trucker.repository.CommunityMemberRepository;
import com.aditya.trucker.repository.CommunityRepository;
import com.aditya.trucker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityMemberService {
    @Autowired
    private CommunityMemberRepository communityMemberRepository;
    
    @Autowired
    private CommunityRepository communityRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CommunityMember joinCommunity(Long userId, Long communityId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        if (communityMemberRepository.findByUser_IdAndCommunity_Id(userId, communityId).isPresent()) {
            throw new RuntimeException("User is already a member of this community");
        }

        CommunityMember member = new CommunityMember(user, community);
        return communityMemberRepository.save(member);
    }

    @Transactional
    public void approveMembership(Long memberId) {
        CommunityMember member = communityMemberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        
        member.approveMembership();
        communityMemberRepository.save(member);
    }

    @Transactional
    public void rejectMembership(Long memberId) {
        CommunityMember member = communityMemberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        
        member.rejectMembership();
        communityMemberRepository.save(member);
    }

    @Transactional
    public void suspendMembership(Long memberId) {
        CommunityMember member = communityMemberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        
        member.suspendMembership();
        communityMemberRepository.save(member);
    }

    @Transactional
    public void removeMembership(Long memberId) {
        CommunityMember member = communityMemberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
        
        member.removeMembership();
        communityMemberRepository.save(member);
    }

    public List<CommunityMember> getCommunityMembers(Long communityId) {
        return communityMemberRepository.findByCommunity_Id(communityId);
    }

    public List<CommunityMember> getUserCommunities(Long userId) {
        return communityMemberRepository.findByUser_Id(userId);
    }

    public Optional<CommunityMember> getMembership(Long userId, Long communityId) {
        return communityMemberRepository.findByUser_IdAndCommunity_Id(userId, communityId);
    }
}
