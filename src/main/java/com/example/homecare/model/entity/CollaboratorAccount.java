package com.example.homecare.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollaboratorAccount {
    @Id
    @Column(name = "collaborator_account_id", nullable = false)
    private String collaboratorAccountId;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable =false)
    private String password;
    @ManyToOne
    @JoinColumn(name = "Collaborator_Id", nullable = false)
    private Collaborator collaborator;
}
