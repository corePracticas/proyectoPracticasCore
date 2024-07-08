package com.practicas.Practicas.controller;

import com.practicas.Practicas.config.jwt.JwtUtil;
import com.practicas.Practicas.model.ApiJwtResponse;
import com.practicas.Practicas.model.ApiResponse;
import com.practicas.Practicas.model.Client;
import com.practicas.Practicas.model.dto.ClientLogin;
import com.practicas.Practicas.service.impl.ClientsService;
import jakarta.validation.Valid;
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
    @Autowired
    private ClientsService clientsService;

    private Client formatearCliente(Client c){
        c.setName(c.getName().trim());
        c.setEmail(c.getEmail().toLowerCase().trim());
        c.setPassword(passwordEncoder.encode(c.getPassword()));
        return c;
    };

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerClient(@Valid @RequestBody Client newClient) {
        try{
            Client client = new Client();
            if(newClient == null){
                return new ResponseEntity<>(new ApiResponse(false, "El cliente debe estar en el formulario"), HttpStatus.BAD_REQUEST);
            }
            if(newClient.getName().isEmpty() || newClient.getEmail().isEmpty() || newClient.getPassword().isEmpty()){
                return new ResponseEntity<>(new ApiResponse(false, "Los campos no pueden estar vacíos"), HttpStatus.BAD_REQUEST);
            }
            clientsService.create(formatearCliente(newClient));
            return new ResponseEntity<>(new ApiResponse(true, "Cliente "+ newClient.getName() + " se ha registrado correctamente"), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<ApiJwtResponse> loginClient(@Valid @RequestBody ClientLogin loginDto) {
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
    @GetMapping("/status/{id}")
    public ResponseEntity<ApiResponse> checkStatus(@PathVariable(name = "id") String id, @RequestHeader("Authorization") String token){
        try {
            ApiResponse response = new ApiResponse();

            if(id.isEmpty()){
                return new ResponseEntity<>(new ApiResponse(false, "El id no puede estar vacío"), HttpStatus.BAD_REQUEST);
            }
            if(jwtUtil.validateToken(token, id.toLowerCase().trim())){
                response.setSuccess(true);
                response.setMessage("Cliente con id: " + id + " está activo");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                System.out.println("Token invalido");
                response.setSuccess(false);
                response.setMessage("Cliente con id: " + id + " no está activo");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
