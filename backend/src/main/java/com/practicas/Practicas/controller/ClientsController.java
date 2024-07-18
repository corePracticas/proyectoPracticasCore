package com.practicas.Practicas.controller;

import com.practicas.Practicas.model.Client;
import com.practicas.Practicas.model.dto.ClientMinified;
import com.practicas.Practicas.service.impl.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("http://localhost:4200")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @GetMapping
    public ResponseEntity<List<ClientMinified>> findAllClients() {
        try {
            List<ClientMinified> clientMinifiedList = clientsService.findAll()
                    .stream()
                    .map(c -> new ClientMinified(c.getId(), c.getName(), c.getRents()))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(clientMinifiedList, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientMinified> findClientById(@PathVariable Long id) {
        try {
            Client client = clientsService.findBy(id);
            if (client != null) {
                ClientMinified clientMinified = new ClientMinified(client.getId(), client.getName(), client.getRents());
                return new ResponseEntity<>(clientMinified, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        try {
            Client createdClient = clientsService.create(client);
            return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client clientDetails) {
        try {
            Client client = clientsService.findBy(id);
            if (client != null) {
                client.setName(clientDetails.getName());
                client.setRents(clientDetails.getRents());
                Client updatedClient = clientsService.create(client);
                return new ResponseEntity<>(updatedClient, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteClient(@PathVariable Long id) {
        try {
            clientsService.deleteBy(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

