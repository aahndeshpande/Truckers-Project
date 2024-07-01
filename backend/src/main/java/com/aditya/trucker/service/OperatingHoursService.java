package com.aditya.trucker.service;

import com.aditya.trucker.model.OperatingHours;
import com.aditya.trucker.repository.OperatingHoursRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperatingHoursService {
    @Autowired
    private OperatingHoursRepo operatingHoursRepo;

    public List<OperatingHours> getAllOperatingHours() {
        return operatingHoursRepo.findAll();
    }

    public Optional<OperatingHours> getOperatingHoursById(Long id) {
        return operatingHoursRepo.findById(id);
    }

    public OperatingHours addOperatingHours(OperatingHours operatingHours) {
        return operatingHoursRepo.save(operatingHours);
    }
    
    public void deleteOperatingHours(Long id) {
        operatingHoursRepo.deleteById(id);
    }

    public OperatingHours updateOperatingHours(Long id, OperatingHours updatedOperatingHours) {
        return operatingHoursRepo.findById(id).map(operatingHours -> {
            operatingHours.setDayOfWeek(updatedOperatingHours.getDayOfWeek());
            operatingHours.setOpenTime(updatedOperatingHours.getOpenTime());
            operatingHours.setCloseTime(updatedOperatingHours.getCloseTime());
            operatingHours.setFoodTruck(updatedOperatingHours.getFoodTruck());
            return operatingHoursRepo.save(operatingHours);
        }).orElseGet(() -> {
            updatedOperatingHours.setId(id);
            return operatingHoursRepo.save(updatedOperatingHours);
        });
    }

}
