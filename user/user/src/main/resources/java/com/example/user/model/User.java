package com.example.user.model;




import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue
    private UUID userId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, length = 10)
    private String mobNum;

    @Column(nullable = false, unique = true)
    private String panNum;


    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt;

    // Getters and Setters


}

