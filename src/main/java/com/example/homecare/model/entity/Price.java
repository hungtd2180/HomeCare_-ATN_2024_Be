package com.example.homecare.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor@AllArgsConstructor
public class Price {
    @Id
    @Column(name = "Price_Id", nullable = false, length = 255)
    private String priceId;
    @Column(name = "type", nullable = false, length = 255)
    private String type;
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Column(name = "price_low")
    private String priceLow;
    @Column(name = "price_high")
    private String priceHigh;
    @ManyToOne
    @JoinColumn(name = "Collaborator_Id", nullable = false)
    private Collaborator collaborator;
}
