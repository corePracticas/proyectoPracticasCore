package com.practicas.Practicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity
@Table(name="rent")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private double price;
    private LocalDate started;
    private LocalDate ended;
    private LocalDate created;
    private LocalDate updated;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    //private Crane crane;
    //Entidad pendiente de crear
    /*@ManyToOne
    @JoinColumn(name = "crane_id", nullable = false)
    private Crane crane;
     */
}
