package com.practicas.Practicas.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grues")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Grue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 60, nullable = false)
    private String name;
    private String type;
    private Integer capacity;
    private String location;
    private boolean available;
    private double pricePerMonth;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "grue", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Rent> rents = new ArrayList<>();
}
