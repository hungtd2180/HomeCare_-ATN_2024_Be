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
public class Visit {
    @Id
    @Column(name = "visit_id", length = 255, nullable = false)
    private String visitId;
    @Column(name = "month", length = 255)
    private String month;
    @Column(name = "year", length = 255)
    private String year;
}
