package com.aditya.trucker.controller;

import com.aditya.trucker.model.CommunityMember;
import com.aditya.trucker.service.CommunityMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/community-members")
public class CommunityMemberController {
    
    @Autowired
    private CommunityMemberService communityMemberService;

    @PostMapping("/join")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommunityMember> joinCommunity(
            @RequestParam Long userId,
            @RequestParam Long communityId) {
        CommunityMember member = communityMemberService.joinCommunity(userId, communityId);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @PostMapping("/approve/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> approveMembership(@PathVariable Long id) {
        communityMemberService.approveMembership(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/reject/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> rejectMembership(@PathVariable Long id) {
        communityMemberService.rejectMembership(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/suspend/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> suspendMembership(@PathVariable Long id) {
        communityMemberService.suspendMembership(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/remove/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> removeMembership(@PathVariable Long id) {
        communityMemberService.removeMembership(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/community/{communityId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CommunityMember>> getCommunityMembers(@PathVariable Long communityId) {
        List<CommunityMember> members = communityMemberService.getCommunityMembers(communityId);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CommunityMember>> getUserCommunities(@PathVariable Long userId) {
        List<CommunityMember> communities = communityMemberService.getUserCommunities(userId);
        return new ResponseEntity<>(communities, HttpStatus.OK);
    }

    @GetMapping("/status/{userId}/{communityId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Optional<CommunityMember>> getMembershipStatus(
            @PathVariable Long userId,
            @PathVariable Long communityId) {
        Optional<CommunityMember> member = communityMemberService.getMembership(userId, communityId);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }
}
