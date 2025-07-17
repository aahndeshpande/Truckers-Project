package com.aditya.trucker.dto;

import com.aditya.trucker.entity.OperatingHours;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Data Transfer Object for OperatingHours entity.
 */
public class OperatingHoursDTO {
    private Long id;
    private DayOfWeek dayOfWeek;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime openTime;
    
    @JsonFormat(pattern = "HH:mm")
    private LocalTime closeTime;
    
    private boolean isOpen;
    private Long foodTruckId;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DayOfWeek getDayOfWeek() { return dayOfWeek; }
    public void setDayOfWeek(DayOfWeek dayOfWeek) { this.dayOfWeek = dayOfWeek; }

    public LocalTime getOpenTime() { return openTime; }
    public void setOpenTime(LocalTime openTime) { this.openTime = openTime; }

    public LocalTime getCloseTime() { return closeTime; }
    public void setCloseTime(LocalTime closeTime) { this.closeTime = closeTime; }

    public boolean isOpen() { return isOpen; }
    public void setOpen(boolean open) { isOpen = open; }

    public Long getFoodTruckId() { return foodTruckId; }
    public void setFoodTruckId(Long foodTruckId) { this.foodTruckId = foodTruckId; }

    /**
     * Converts an OperatingHours entity to an OperatingHoursDTO.
     *
     * @param operatingHours the OperatingHours entity to convert
     * @return the converted OperatingHoursDTO
     */
    public static OperatingHoursDTO fromEntity(OperatingHours operatingHours) {
        if (operatingHours == null) {
            return null;
        }

        OperatingHoursDTO dto = new OperatingHoursDTO();
        dto.setId(operatingHours.getId());
        dto.setDayOfWeek(operatingHours.getDayOfWeek());
        dto.setOpenTime(operatingHours.getOpenTime());
        dto.setCloseTime(operatingHours.getCloseTime());
        dto.setOpen(operatingHours.isOpen());

        if (operatingHours.getFoodTruck() != null) {
            dto.setFoodTruckId(operatingHours.getFoodTruck().getId());
        }

        return dto;
    }

    /**
     * Converts this DTO to an OperatingHours entity.
     *
     * @return the converted OperatingHours entity
     */
    public OperatingHours toEntity() {
        OperatingHours operatingHours = new OperatingHours();
        operatingHours.setId(this.id);
        operatingHours.setDayOfWeek(this.dayOfWeek);
        operatingHours.setOpenTime(this.openTime);
        operatingHours.setCloseTime(this.closeTime);
        operatingHours.setOpen(this.isOpen);
        
        // Note: FoodTruck must be set separately as it requires a database lookup
        
        return operatingHours;
    }

    @Override
    public String toString() {
        return "OperatingHoursDTO{" +
                "dayOfWeek=" + dayOfWeek +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", isOpen=" + isOpen +
                '}';
    }
}
