package com.aditya.trucker.service;

import com.aditya.trucker.model.Event;
import com.aditya.trucker.model.Community;
import com.aditya.trucker.model.Owner;
import com.aditya.trucker.model.FoodTruck;
import com.aditya.trucker.repository.EventRepository;
import com.aditya.trucker.repository.CommunityRepository;
import com.aditya.trucker.repository.OwnerRepository;
import com.aditya.trucker.repository.FoodTruckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private CommunityRepository communityRepository;
    
    @Autowired
    private OwnerRepository ownerRepository;
    
    @Autowired
    private FoodTruckRepository foodTruckRepository;

    @Transactional
    public Event createEvent(Long ownerId, Long communityId, String name, String description,
                           String location, String address, String city, String state,
                           String zipCode, LocalDateTime startTime, LocalDateTime endTime,
                           int capacity, String imageUrl, boolean isPublic,
                           String contactPerson, String contactEmail, String contactPhone,
                           String websiteUrl, String socialMediaUrl) {
        Owner owner = ownerRepository.findById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        
        Community community = communityRepository.findById(communityId)
                .orElseThrow(() -> new RuntimeException("Community not found"));

        Event event = new Event(name, description, location, address, city, state,
                zipCode, startTime, endTime, capacity, imageUrl, isPublic,
                contactPerson, contactEmail, contactPhone, websiteUrl, socialMediaUrl);
        
        event.setOwner(owner);
        event.setCommunity(community);
        
        return eventRepository.save(event);
    }

    @Transactional
    public Event updateEvent(Long eventId, String name, String description,
                           String location, String address, String city, String state,
                           String zipCode, LocalDateTime startTime, LocalDateTime endTime,
                           int capacity, String imageUrl, boolean isPublic,
                           String contactPerson, String contactEmail, String contactPhone,
                           String websiteUrl, String socialMediaUrl) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        event.setName(name);
        event.setDescription(description);
        event.setLocation(location);
        event.setAddress(address);
        event.setCity(city);
        event.setState(state);
        event.setZipCode(zipCode);
        event.setStartTime(startTime);
        event.setEndTime(endTime);
        event.setCapacity(capacity);
        event.setImageUrl(imageUrl);
        event.setPublic(isPublic);
        event.setContactPerson(contactPerson);
        event.setContactEmail(contactEmail);
        event.setContactPhone(contactPhone);
        event.setWebsiteUrl(websiteUrl);
        event.setSocialMediaUrl(socialMediaUrl);

        return eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        eventRepository.delete(event);
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public List<Event> getEventsByCommunity(Long communityId) {
        return eventRepository.findByCommunity_Id(communityId);
    }

    public List<Event> getEventsByOwner(Long ownerId) {
        return eventRepository.findByOwner_Id(ownerId);
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findByStartTimeAfterOrderByStartTimeAsc(LocalDateTime.now());
    }

    public List<Event> getPastEvents() {
        return eventRepository.findByEndTimeBeforeOrderByEndTimeDesc(LocalDateTime.now());
    }

    public Page<Event> searchEvents(String query, Pageable pageable) {
        return eventRepository.searchEvents(query, pageable);
    }

    public List<Event> getEventsByFoodTruck(Long foodTruckId) {
        return eventRepository.findByFoodTrucks_Id(foodTruckId);
    }

    @Transactional
    public void addFoodTruckToEvent(Long eventId, Long foodTruckId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
                .orElseThrow(() -> new RuntimeException("Food truck not found"));

        event.addFoodTruck(foodTruck);
        eventRepository.save(event);
    }

    @Transactional
    public void removeFoodTruckFromEvent(Long eventId, Long foodTruckId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        FoodTruck foodTruck = foodTruckRepository.findById(foodTruckId)
                .orElseThrow(() -> new RuntimeException("Food truck not found"));

        event.removeFoodTruck(foodTruck);
        eventRepository.save(event);
    }

    public boolean isEventFull(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        return event.getCapacity() <= event.getParticipants().size();
    }

    public double getAverageRating(Long eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));
        
        List<EventReview> reviews = event.getReviews();
        if (reviews.isEmpty()) return 0;
        
        double sum = reviews.stream()
                .mapToInt(EventReview::getRating)
                .sum();
        
        return sum / reviews.size();
    }

    public List<Event> getEventsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return eventRepository.findByStartTimeBetween(startDate, endDate);
    }
}
