package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event_participants")
@EqualsAndHashCode(of = "id")
public class EventParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "registered_at", nullable = false, updatable = false)
    private LocalDateTime registeredAt = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ParticipationStatus status = ParticipationStatus.PENDING;

    @Column(name = "number_of_guests", nullable = false, columnDefinition = "int default 1")
    private int numberOfGuests = 1;

    @Column(name = "special_requests", length = 1000)
    private String specialRequests;

    public EventParticipant(User user, Event event, int numberOfGuests, String specialRequests) {
        this.user = user;
        this.event = event;
        this.registeredAt = LocalDateTime.now();
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

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
