package com.example.homecare.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "Id", nullable = false, length = 255)
    private String id;
    @Column(name = "Customer_Id", nullable = false, length = 255)
    private String customerId;

    @Column(name = "Address", nullable = false, length = 255)
    private String address;

    @Column(name = "Phone", nullable = false, length = 255)
    private String phone;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "Email", length = 255)
    private String email;
    @Column(name = "Time")
    private String time;
    @ManyToOne
    @JoinColumn(name = "Collaborator_Id", nullable = false)
    private Collaborator collaborator;
}
