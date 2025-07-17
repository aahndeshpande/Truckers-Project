package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a customer in the system.
 * Extends the base User class with customer-specific fields and relationships.
 */
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "user_id")
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
@AttributeOverrides({
    @AttributeOverride(name = "username", column = @Column(name = "username", nullable = false, length = 50)),
    @AttributeOverride(name = "password", column = @Column(name = "password_hash", nullable = false, length = 255)),
    @AttributeOverride(name = "email", column = @Column(name = "email", nullable = false, length = 100))
})
public class Customer extends User {
    
    @Column(name = "customer_phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Review> customerReviews = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<EventParticipant> customerEventParticipations = new ArrayList<>();

    /**
     * Creates a new Customer instance.
     *
     * @param username  The username for the customer
     * @param email     The email address of the customer
     * @param password  The hashed password
     * @param phoneNumber The contact phone number
     * @param address   The street address
     * @param city      The city
     * @param state     The state/province
     * @param zipCode   The postal/zip code
     */
    public Customer(String username, String email, String password, String phoneNumber,
                   String address, String city, String state, String zipCode) {
        super(username, password, email, UserRole.CUSTOMER);
        this.phoneNumber = phoneNumber;
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setZipCode(zipCode);
        this.setPhoneNumber(phoneNumber); // Set phone number in parent class as well
    }

    /**
     * Adds a review to this customer's profile.
     *
     * @param review The review to add
     */
    public void addReview(Review review) {
        if (review != null) {
            review.setCustomer(this);
            this.customerReviews.add(review);
        }
    }

    /**
     * Removes a review from this customer's profile.
     *
     * @param review The review to remove
     */
    public void removeReview(Review review) {
        if (review != null) {
            review.setCustomer(null);
            this.customerReviews.remove(review);
        }
    }

    /**
     * Adds an event participation record for this customer.
     *
     * @param participation The event participation to add
     */
    public void addEventParticipation(EventParticipant participation) {
        if (participation != null) {
            participation.setCustomer(this);
            this.customerEventParticipations.add(participation);
        }
    }

    /**
     * Removes an event participation record from this customer.
     *
     * @param participation The event participation to remove
     */
    public void removeEventParticipation(EventParticipant participation) {
        if (participation != null) {
            participation.setCustomer(null);
            this.customerEventParticipations.remove(participation);
        }
    }
}
