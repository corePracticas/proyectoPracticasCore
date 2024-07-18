package com.practicas.Practicas.model.dto;

import com.practicas.Practicas.model.Rent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientMinified {
    private long id;
    private String name;
    private List<Rent> rents;
}
