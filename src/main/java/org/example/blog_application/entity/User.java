package org.example.blog_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.blog_application.model.enums.UserRole;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(name = "username")
    private String username;
    private String passwordHash;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "avatar")
    private String avatar;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @PrePersist
    public void logNewPostAttempt() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    public void logPostUpdateAttempt() {
        this.updatedAt = new Date();
    }
}
