package com.aditya.trucker.controller;

import com.aditya.trucker.model.Community;
import com.aditya.trucker.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/communities")
public class CommunityController {
    
    @Autowired
    private CommunityService communityService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Community>> getAllCommunities() {
        return ResponseEntity.ok(communityService.getAllCommunities());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Community> getCommunityById(@PathVariable Long id) {
        Community community = communityService.getCommunityById(id);
        return ResponseEntity.ok(community);
    }

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Community> createCommunity(
            @RequestParam Long ownerId,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String zipCode,
            @RequestParam String contactPerson,
            @RequestParam String contactEmail,
            @RequestParam String contactPhone,
            @RequestParam String description,
            @RequestParam String websiteUrl,
            @RequestParam String socialMediaUrl,
            @RequestParam String imageUrl) {
        Community community = communityService.createCommunity(
                ownerId, name, address, city, state, zipCode,
                contactPerson, contactEmail, contactPhone, description,
                websiteUrl, socialMediaUrl, imageUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(community);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Community> updateCommunity(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String city,
            @RequestParam String state,
            @RequestParam String zipCode,
            @RequestParam String contactPerson,
            @RequestParam String contactEmail,
            @RequestParam String contactPhone,
            @RequestParam String description,
            @RequestParam String websiteUrl,
            @RequestParam String socialMediaUrl,
            @RequestParam String imageUrl) {
        Community community = communityService.updateCommunity(
                id, name, address, city, state, zipCode,
                contactPerson, contactEmail, contactPhone, description,
                websiteUrl, socialMediaUrl, imageUrl);
        return ResponseEntity.ok(community);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> deleteCommunity(@PathVariable Long id) {
        communityService.deleteCommunity(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/owner/{ownerId}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<List<Community>> getCommunitiesByOwner(@PathVariable Long ownerId) {
        List<Community> communities = communityService.getCommunitiesByOwner(ownerId);
        return ResponseEntity.ok(communities);
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Community>> searchCommunities(@RequestParam String query) {
        List<Community> communities = communityService.searchCommunities(query);
        return ResponseEntity.ok(communities);
    }

    @GetMapping("/location")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Page<Community>> getCommunitiesByLocation(
            @RequestParam String city,
            @RequestParam String state,
            Pageable pageable) {
        Page<Community> communities = communityService.getCommunitiesByLocation(city, state, pageable);
        return ResponseEntity.ok(communities);
    }

    @GetMapping("/food-truck/{foodTruckId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Community>> getCommunitiesByFoodTruck(@PathVariable Long foodTruckId) {
        List<Community> communities = communityService.getCommunitiesByFoodTruck(foodTruckId);
        return ResponseEntity.ok(communities);
    }

    @GetMapping("/event/{eventId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Community>> getCommunitiesByEvent(@PathVariable Long eventId) {
        List<Community> communities = communityService.getCommunitiesByEvent(eventId);
        return ResponseEntity.ok(communities);
    }

    @PostMapping("/add-food-truck/{communityId}/{foodTruckId}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> addFoodTruckToCommunity(
            @PathVariable Long communityId,
            @PathVariable Long foodTruckId) {
        communityService.addFoodTruckToCommunity(communityId, foodTruckId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/remove-food-truck/{communityId}/{foodTruckId}")
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<Void> removeFoodTruckFromCommunity(
            @PathVariable Long communityId,
            @PathVariable Long foodTruckId) {
        communityService.removeFoodTruckFromCommunity(communityId, foodTruckId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/member-count/{communityId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Integer> getMemberCount(@PathVariable Long communityId) {
        int count = communityService.getMemberCount(communityId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/popular")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Community>> getPopularCommunities() {
        List<Community> communities = communityService.getPopularCommunities();
        return ResponseEntity.ok(communities);
    }

    @GetMapping("/upcoming-events")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Community>> getCommunitiesWithUpcomingEvents() {
        List<Community> communities = communityService.getCommunitiesWithUpcomingEvents();
        return ResponseEntity.ok(communities);
    }

    @GetMapping("/rating")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Community>> getCommunitiesByRating() {
        List<Community> communities = communityService.getCommunitiesByRating();
        return ResponseEntity.ok(communities);
    }

    @GetMapping("/user/{userId}/{communityId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Boolean> isUserMember(
            @PathVariable Long userId,
            @PathVariable Long communityId) {
        boolean isMember = communityService.isUserMember(userId, communityId);
        return ResponseEntity.ok(isMember);
    }

    @GetMapping("/category")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Community>> getCommunitiesByCategory(@RequestParam String category) {
        List<Community> communities = communityService.getCommunitiesByCategory(category);
        return ResponseEntity.ok(communities);
    }

    @GetMapping("/nearby")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Community>> getCommunitiesByDistance(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam double radius) {
        List<Community> communities = communityService.getCommunitiesByDistance(latitude, longitude, radius);
        return ResponseEntity.ok(communities);
    }
        communityService.deleteCommunity(id);
        return ResponseEntity.noContent().build();
    }
}
