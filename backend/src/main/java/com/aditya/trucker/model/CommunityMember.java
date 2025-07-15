package com.aditya.trucker.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
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
}
