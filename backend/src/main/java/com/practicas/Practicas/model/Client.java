package com.practicas.Practicas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private long id;
    @Size(min = 6, max = 60, message = "El nombre debe tener entre 6 y 60 caracteres")
    @Column(length = 60, nullable = false)
    private String name;
    @Size(min = 6, max = 30, message = "El email debe tener entre 6 y 30 caracteres")
    @Email(message = "El email debe ser válido")
    @Column(length = 30, unique = true, nullable = false)
    private String email;
    @Size(min = 6, max = 60, message = "La contraseña debe tener entre 6 y 60 caracteres")
    private String password;
    private LocalDate createdAt = LocalDate.now();
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "client")
    private List<Rent> rents;
}
