package com.example.homecare.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column(name = "user_id", nullable = false, length = 255)
    private String userId;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "Phone", nullable = false, length = 255)
    private String phone;

    @Column(name = "Email", length = 255)
    private String email;

    @Column(name = "Address", length = 1000)
    private String address;

    @Column(name = "Password", nullable = false, length = 255)
    private String password;
    @Column(name = "Time")
    private String time;
}
