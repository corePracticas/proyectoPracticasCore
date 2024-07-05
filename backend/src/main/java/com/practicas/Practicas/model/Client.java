package com.practicas.Practicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "client")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(length = 60,nullable = false)
    private String name;
    @Column(length = 30,unique = true, nullable = false)
    private String email;
    @Column(length = 255,nullable = false)
    private String password;
    private LocalDate created = LocalDate.now();
    private LocalDate updated;
    @OneToMany(mappedBy = "client")
    private List<Rent> rents;
}
