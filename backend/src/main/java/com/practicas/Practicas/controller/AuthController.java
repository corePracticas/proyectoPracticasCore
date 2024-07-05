package com.practicas.Practicas.controller;

import com.practicas.Practicas.config.jwt.JwtUtil;
import com.practicas.Practicas.dto.Cliente.LoginDto;
import com.practicas.Practicas.model.ApiJwtResponse;
import com.practicas.Practicas.model.ApiResponse;
import com.practicas.Practicas.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Validated
// TODO: REMOVER *
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;
    // Autowire CRUD repo

    private Client formatearCliente(Client c){
        c.setName(c.getName().trim());
        c.setEmail(c.getEmail().toLowerCase().trim());
        c.setPassword(passwordEncoder.encode(c.getPassword()));
        return c;
    };

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerClient(@RequestBody Client newClient) {
        try{
            if(newClient == null){
                return new ResponseEntity<>(new ApiResponse(false, "La información del cliente debe estar completa"), HttpStatus.BAD_REQUEST);
            }
            //TODO: Añadir lógica y validaciones
            return new ResponseEntity<>(new ApiResponse(true, "Cliente "+ newClient.getName() + " se ha registrado correctamente"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ApiJwtResponse> loginClient(@RequestBody LoginDto loginDto) {
        try {
            //TODO: Check if user exists if it does return token
            String token = jwtUtil.generateToken(loginDto.getEmail());
            ApiJwtResponse response = new ApiJwtResponse();
            // TODO: Remplazar con lógica de autenticación
            boolean isUser = true;
            if(isUser){
                response.setSuccess(true);
                response.setMessage("Login exitoso");
                response.setToken(token);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            return new ResponseEntity<>(new ApiJwtResponse(false, "Credenciales incorrectas", "ERROR"), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiJwtResponse(false, e.getMessage(), "ERROR"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/status/{email}")
    public ResponseEntity<ApiResponse> checkStatus(@PathVariable String email, @RequestHeader("Authorization") String token){
        try {
            ApiResponse response = new ApiResponse();

            if(email.isEmpty()){
                return new ResponseEntity<>(new ApiResponse(false, "El email no puede estar vacío"), HttpStatus.BAD_REQUEST);
            }
            if(jwtUtil.validateToken(token, email.toLowerCase().trim())){
                response.setSuccess(true);
                response.setMessage("Cliente con email: " + email + " está activo");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                System.out.println("Token invalido");
                response.setSuccess(false);
                response.setMessage("Cliente con email: " + email + " no está activo");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
