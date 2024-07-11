package com.practicas.Practicas.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class GrueWithCapacity {

    private long id;
    private String name;
    private String type;
    private Integer capacity;
    private String location;
    private boolean available;
    private double pricePerMonth;
    private LocalDate createdAt ;
    private LocalDate updatedAt;
}
