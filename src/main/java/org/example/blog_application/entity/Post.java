package org.example.blog_application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "public_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date publicAt;
    @Column(name = "title")
    private String title;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    @Column(name = "thumbnail")
    private String thumbnail;
    @Column(name = "status")
    private Boolean status;

    @ManyToMany
    @JoinTable(name = "post_category",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categoryList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void logNewPostAttempt() {
       this.createdAt = new Date();
       this.updatedAt = new Date();
       if (this.status) {
           this.publicAt = new Date();
       } else {
           this.publicAt = null;
       }
    }

    @PreUpdate
    public void logPostUpdateAttempt() {
        this.updatedAt = new Date();
        if (this.status) {
            this.publicAt = new Date();
        } else {
            this.publicAt = null;
        }
    }
}
