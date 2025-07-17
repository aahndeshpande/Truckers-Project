package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "community_members")
public class CommunityMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private LocalDateTime joinedDate;

    @Column(nullable = false)
    private boolean isApproved;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String status;

    public CommunityMember(User user, Community community) {
        this.user = user;
        this.community = community;
        this.joinedDate = LocalDateTime.now();
        this.isApproved = false;
        this.role = "MEMBER";
        this.status = "PENDING";
    }

    public void approveMembership() {
        this.isApproved = true;
        this.status = "ACTIVE";
    }

    public void rejectMembership() {
        this.status = "REJECTED";
    }

    public void suspendMembership() {
        this.status = "SUSPENDED";
    }

    public void removeMembership() {
        this.status = "REMOVED";
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
