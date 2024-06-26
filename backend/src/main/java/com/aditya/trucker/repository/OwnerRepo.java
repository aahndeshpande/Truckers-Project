package com.aditya.trucker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aditya.trucker.model.Owner;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends JpaRepository<Owner, Long> {

}
