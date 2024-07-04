package com.practicas.Practicas.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;

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
    @Column(length = 30,unique = true,nullable = false)
    private String email;
    @Column(length = 255,nullable = false)
    private String password;
    private LocalDate created;
    private LocalDate updated;
}
