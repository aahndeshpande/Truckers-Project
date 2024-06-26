package com.aditya.trucker.repository;

import org.springframework.stereotype.Repository;
import com.aditya.trucker.model.OperatingHours;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OperatingHoursRepo extends JpaRepository<OperatingHours, Long>{

}
