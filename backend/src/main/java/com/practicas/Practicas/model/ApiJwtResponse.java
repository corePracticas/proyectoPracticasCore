package com.practicas.Practicas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiJwtResponse {
    private boolean success;
    private String message;
    private String token;
}
