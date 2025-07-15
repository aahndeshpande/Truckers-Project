package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event_participants")
public class EventParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime registeredDate;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private int numberOfGuests;

    @Column(nullable = false)
    private String specialRequests;

    public EventParticipant(User user, Event event, int numberOfGuests, String specialRequests) {
        this.user = user;
        this.event = event;
        this.registeredDate = LocalDateTime.now();
        this.status = "REGISTERED";
        this.numberOfGuests = numberOfGuests;
        this.specialRequests = specialRequests;
    }

    public void cancelParticipation() {
        this.status = "CANCELLED";
    }

    public void confirmAttendance() {
        this.status = "ATTENDING";
    }

    public void markAsNoShow() {
        this.status = "NO_SHOW";
    }
}
