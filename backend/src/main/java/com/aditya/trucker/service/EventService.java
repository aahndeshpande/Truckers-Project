package com.aditya.trucker.service;

import com.aditya.trucker.model.Event;
import com.aditya.trucker.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepo eventRepo;

    public List<Event> getAllEvents() {
        return eventRepo.findAll();
    }

    public Optional<Event> getEventById(Long id) {
        return eventRepo.findById(id);
    }

    public Event addEvent(Event event) {
        return eventRepo.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepo.deleteById(id);
    }

    public Event updateEvent(Long id, Event updatedEvent) {
        return eventRepo.findById(id).map(event -> {
            event.setName(updatedEvent.getName());
            event.setDate(updatedEvent.getDate());
            event.setDescription(updatedEvent.getDescription());
            event.setCommunity(updatedEvent.getCommunity());
            event.setFoodTrucks(updatedEvent.getFoodTrucks());
            return eventRepo.save(event);
        }).orElseGet(() -> {
            updatedEvent.setId(id);
            return eventRepo.save(updatedEvent);
        });
    }

}
