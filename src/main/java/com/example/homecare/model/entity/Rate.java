package com.example.homecare.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    @Id
    @Column(name = "Rate_Id", nullable = false)
    private Integer rateId;

    @Column(name = "Comment", length = 1000)
    private String comment;

    @Column(name = "Star", nullable = false)
    private Integer star;

    @Column(name = "Id_Reply", nullable = false)
    private Integer idReply;

    @ManyToOne
    @JoinColumn(name = "User_Id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "Collaborator_Id", nullable = false)
    private Collaborator collaborator;
}
