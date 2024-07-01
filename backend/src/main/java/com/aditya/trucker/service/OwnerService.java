package com.aditya.trucker.service;

import com.aditya.trucker.model.Owner;
import com.aditya.trucker.repository.OwnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepo ownerRepo;

    public List<Owner> getAllOwners() {
        return ownerRepo.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepo.findById(id);
    }

    public Owner addOwner(Owner owner) {
        return ownerRepo.save(owner);
    }

    public void deleteOwner(Long id) {
        ownerRepo.deleteById(id);
    }

    public Owner updateOwner(Long id, Owner updatedOwner) {
        return ownerRepo.findById(id).map(owner -> {
            owner.setName(updatedOwner.getName());
            owner.setEmail(updatedOwner.getEmail());
            owner.setReviews(updatedOwner.getReviews());
            return ownerRepo.save(owner);
        }).orElseGet(() -> {
            updatedOwner.setId(id);
            return ownerRepo.save(updatedOwner);
        });
    }
}
