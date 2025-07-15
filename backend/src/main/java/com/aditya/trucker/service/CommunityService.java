package com.aditya.trucker.service;

import com.aditya.trucker.model.Community;
import com.aditya.trucker.model.Owner;
import com.aditya.trucker.model.User;
import com.aditya.trucker.repository.CommunityRepository;
import com.aditya.trucker.repository.OwnerRepository;
import com.aditya.trucker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {
    @Autowired
    private CommunityRepository communityRepository;
    
    @Autowired
    private OwnerRepository ownerRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Community createCommunity(Long ownerId, String name, String address, String city,
                                   String state, String zipCode, String contactPerson,
                                   String contactEmail, String contactPhone,
                                   String description, String websiteUrl,
                                   String socialMediaUrl, String imageUrl) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Community community = new Community(name, address, city, state, zipCode,
                contactPerson, contactEmail, contactPhone, description,
                websiteUrl, imageUrl, socialMediaUrl);
        
        community.setOwner(owner);
        return communityRepository.save(community);
    }

    @Transactional
    public Community updateCommunity(Long communityId, String name, String address, String city,
                                   String state, String zipCode, String contactPerson,
                                   String contactEmail, String contactPhone,
                                   String description, String websiteUrl,
                                   String socialMediaUrl, String imageUrl) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        community.setName(name);
        community.setAddress(address);
        community.setCity(city);
        community.setState(state);
        community.setZipCode(zipCode);
        community.setContactPerson(contactPerson);
        community.setContactEmail(contactEmail);
        community.setContactPhone(contactPhone);
        community.setDescription(description);
        community.setWebsiteUrl(websiteUrl);
        community.setSocialMediaUrl(socialMediaUrl);
        community.setImageUrl(imageUrl);

        return communityRepository.save(community);
    }

    @Transactional
    public void deleteCommunity(Long communityId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));
        
        communityRepository.delete(community);
    }

    public Community getCommunityById(Long communityId) {
        return communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));
    }

    public List<Community> getCommunitiesByOwner(Long ownerId) {
        return communityRepository.findByOwner_Id(ownerId);
    }

    public List<Community> searchCommunities(String query) {
        return communityRepository.searchCommunities(query);
    }

    public Page<Community> getCommunitiesByLocation(String city, String state, Pageable pageable) {
        return communityRepository.findByCityAndState(city, state, pageable);
    }

    public List<Community> getCommunitiesByFoodTruck(Long foodTruckId) {
        return communityRepository.findByFoodTrucks_Id(foodTruckId);
    }

    public List<Community> getCommunitiesByEvent(Long eventId) {
        return communityRepository.findByEvents_Id(eventId);
    }

    @Transactional
    public void addFoodTruckToCommunity(Long communityId, Long foodTruckId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));
        
        FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
                .orElseThrow(() -> new RuntimeException("Food truck not found"));

        community.addFoodTruck(foodTruck);
        communityRepository.save(community);
    }

    @Transactional
    public void removeFoodTruckFromCommunity(Long communityId, Long foodTruckId) {
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));
        
        FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
                .orElseThrow(() -> new RuntimeException("Food truck not found"));

        community.removeFoodTruck(foodTruck);
        communityRepository.save(community);
    }

    public int getMemberCount(Long communityId) {
        return communityRepository.countMembersByCommunityId(communityId);
    }

    public List<Community> getPopularCommunities() {
        return communityRepository.findTopByOrderByMemberCountDesc();
    }

    public List<Community> getCommunitiesWithUpcomingEvents() {
        return communityRepository.findByEvents_StartTimeAfter(LocalDateTime.now());
    }

    public List<Community> getCommunitiesByRating() {
        return communityRepository.findByOrderByAverageRatingDesc();
    }

    public boolean isUserMember(Long userId, Long communityId) {
        return communityRepository.existsByMembers_User_Id(userId, communityId);
    }

    public List<Community> getCommunitiesByCategory(String category) {
        return communityRepository.findByCategory(category);
    }

    public List<Community> getCommunitiesByDistance(double latitude, double longitude, double radius) {
        return communityRepository.findByLocationWithin(latitude, longitude, radius);
    }
}