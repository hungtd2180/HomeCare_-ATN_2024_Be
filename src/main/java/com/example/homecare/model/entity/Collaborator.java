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
public class Collaborator {
    @Id
    @Column(name = "Collaborator_Id", nullable = false, length = 255)
    private String collaboratorId;

    @Column(name = "Image", length = 255)
    private String image;

    @Column(name = "Username", nullable = false, length = 255)
    private String username;

    @Column(name = "Email", length = 255)
    private String email;

    @Column(name = "Phone", nullable = false, length = 255)
    private String phone;

    @Column(name = "Name", nullable = false, length = 255)
    private String name;

    @Column(name = "Address", nullable = false, length = 255)
    private String address;

    @Column(name = "Description", length = 10000)
    private String description;

    @Column(name = "Time_Start")
    private String timeStart;

    @Column(name = "Field")
    private String field;

    @Column(name = "Password")
    private String password;

    @Column(name = "Time_End")
    private String timeEnd;

    @Column(name = "Status", nullable = false)
    private Integer status;
    @Column(name = "Time")
    private String time;
}
