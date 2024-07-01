package com.aditya.trucker.service;

import com.aditya.trucker.model.Schedule;
import com.aditya.trucker.repository.ScheduleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepo scheduleRepo;

    public List<Schedule> getAllSchedules() {
        return scheduleRepo.findAll();
    }

    public Optional<Schedule> getScheduleById(Long id) {
        return scheduleRepo.findById(id);
    }

    public Schedule addSchedule(Schedule schedule) {
        return scheduleRepo.save(schedule);
    }

    public Schedule updateSchedule(Long id, Schedule updatedSchedule) {
        return scheduleRepo.findById(id).map(schedule -> {
            schedule.setDateTime(updatedSchedule.getDateTime());
            schedule.setStartTime(updatedSchedule.getStartTime());
            schedule.setEndTime(updatedSchedule.getEndTime());
            schedule.setFoodTruck(updatedSchedule.getFoodTruck());
            schedule.setCommunity(updatedSchedule.getCommunity());
            return scheduleRepo.save(schedule);
        }).orElseGet(() -> {
            updatedSchedule.setId(id);
            return scheduleRepo.save(updatedSchedule);
        });
    }

    public void deleteSchedule(Long id) {
        scheduleRepo.deleteById(id);
    }
}
