package com.ToDoApp.ToDoApp.models;

import com.ToDoApp.ToDoApp.Enums.Priority;
import com.ToDoApp.ToDoApp.Enums.Status;
import com.ToDoApp.ToDoApp.Responses.UsersResponseDTO;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name ="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    private String description;

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String attachmentUrl; // S3 URL for image/pdf

    @Column(columnDefinition = "TEXT")
    private String attachment; // Convert the imgs/pdfs into base64

    private LocalDateTime updated_at;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;

    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDateTime.now();
    }

}

