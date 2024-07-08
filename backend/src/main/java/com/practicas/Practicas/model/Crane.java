package com.practicas.Practicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "crane")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Crane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 50)
    private String name;
    @Column(length = 250)
    private String tipe;
    private int capacity;
    @Column(length = 250)
    private String ubication;
    @Column(nullable = false)
    private Boolean available;
    private double price_for_month;
    @OneToMany(mappedBy = "crane")
    private List<Rent> rents;

}
