package com.practicas.Practicas.model.dto;

import com.practicas.Practicas.model.enums.RentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentsActives {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private RentStatus status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private long idGrue;
    private long idClient;
    private String username;
}
