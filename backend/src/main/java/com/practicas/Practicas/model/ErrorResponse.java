package com.practicas.Practicas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private int status;
    private String message;
    private List<String> errors;
}