package com.aditya.trucker.service;

import com.aditya.trucker.model.Community;
import com.aditya.trucker.repository.CommunityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {
    @Autowired
    private CommunityRepo communityRepo;

    public List<Community> getAllCommunities() {
        return communityRepo.findAll();
    }

    public Optional<Community> getCommunityById(Long id) {
        return communityRepo.findById(id);
    }

    public Community addCommunity(Community community) {
        return communityRepo.save(community);
    }

    public Community updateCommunity(Long id, Community updatedCommunity) {
        return communityRepo.findById(id).map(community -> {
            community.setName(updatedCommunity.getName());
            community.setAddress(updatedCommunity.getAddress());
            community.setContactPerson(updatedCommunity.getContactPerson());
            community.setContactEmail(updatedCommunity.getContactEmail());
            return communityRepo.save(community);
        }).orElseGet(() -> {
            updatedCommunity.setId(id);
            return communityRepo.save(updatedCommunity);
        });
    }

    public void deleteCommunity(Long id) {
        communityRepo.deleteById(id);
    }
}