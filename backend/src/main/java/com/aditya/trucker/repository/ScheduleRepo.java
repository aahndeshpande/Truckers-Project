package com.aditya.trucker.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.aditya.trucker.model.Schedule;

@Repository
public interface ScheduleRepo extends JpaRepository<Schedule, Long> {

}
