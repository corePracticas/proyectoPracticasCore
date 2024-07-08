package com.practicas.Practicas.model;

import com.practicas.Practicas.model.enums.RentStatus;
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
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private RentStatus status;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate updatedAt;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "grue_id", nullable = false)
    private Grue grue;
}
