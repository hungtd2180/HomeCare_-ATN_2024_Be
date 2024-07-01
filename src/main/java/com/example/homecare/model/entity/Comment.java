package com.example.homecare.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor@AllArgsConstructor
public class Comment {
    @Id
    @Column(name = "comment_id", nullable = false, length = 255)
    private String commentId;
    @Column(name = "content", nullable = false, length = 255)
    private String content;
    @Column(name = "time")
    private String time;
    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Collection_Id", nullable = false)
    private Collaborator collaborator;
}
