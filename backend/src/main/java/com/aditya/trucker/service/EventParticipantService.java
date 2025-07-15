package com.aditya.trucker.service;

import com.aditya.trucker.model.Event;
import com.aditya.trucker.model.EventParticipant;
import com.aditya.trucker.model.User;
import com.aditya.trucker.repository.EventParticipantRepository;
import com.aditya.trucker.repository.EventRepository;
import com.aditya.trucker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventParticipantService {
    @Autowired
    private EventParticipantRepository eventParticipantRepository;
    
    @Autowired
    private EventRepository eventRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public EventParticipant registerForEvent(Long userId, Long eventId, int numberOfGuests, String specialRequests) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (eventParticipantRepository.findByUser_IdAndEvent_Id(userId, eventId).isPresent()) {
            throw new RuntimeException("User is already registered for this event");
        }

        if (event.getCapacity() <= eventParticipantRepository.countByEvent_Id(eventId)) {
            throw new RuntimeException("Event is at full capacity");
        }

        EventParticipant participant = new EventParticipant(user, event, numberOfGuests, specialRequests);
        return eventParticipantRepository.save(participant);
    }

    @Transactional
    public void cancelRegistration(Long participantId) {
        EventParticipant participant = eventParticipantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        
        participant.cancelParticipation();
        eventParticipantRepository.save(participant);
    }

    @Transactional
    public void confirmAttendance(Long participantId) {
        EventParticipant participant = eventParticipantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        
        participant.confirmAttendance();
        eventParticipantRepository.save(participant);
    }

    @Transactional
    public void markAsNoShow(Long participantId) {
        EventParticipant participant = eventParticipantRepository.findById(participantId)
                .orElseThrow(() -> new RuntimeException("Participant not found"));
        
        participant.markAsNoShow();
        eventParticipantRepository.save(participant);
    }

    public List<EventParticipant> getEventParticipants(Long eventId) {
        return eventParticipantRepository.findByEvent_Id(eventId);
    }

    public List<EventParticipant> getUserEvents(Long userId) {
        return eventParticipantRepository.findByUser_Id(userId);
    }

    public Optional<EventParticipant> getRegistration(Long userId, Long eventId) {
        return eventParticipantRepository.findByUser_IdAndEvent_Id(userId, eventId);
    }
}
