package com.practicas.Practicas.model.dto;

import com.practicas.Practicas.model.Client;
import com.practicas.Practicas.model.Grue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentStatus {
    private long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private com.practicas.Practicas.model.enums.RentStatus status;
    private LocalDate createdAt ;
    private LocalDate updatedAt;


    private String  name;

   private long idGrua;
}
