package com.example.homecare.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    @Column(name = "Notification_Id", nullable = false)
    private Integer notificationId;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "From_Id", nullable = false)
    private String fromId;

    @Column(name = "Status", nullable = false)
    private String status;
    @Column(name = "Time", nullable = false)
    private String time;
    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Collaborator_Id", nullable = false)
    private Collaborator collaborator;
}
