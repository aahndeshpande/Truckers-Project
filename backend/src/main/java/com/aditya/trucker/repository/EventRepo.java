package com.aditya.trucker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aditya.trucker.model.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepo extends JpaRepository<Event, Long> {

}
