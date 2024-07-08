package com.practicas.Practicas.controller;

import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.model.Rent;
import com.practicas.Practicas.model.enums.RentStatus;
import com.practicas.Practicas.service.IGrueService;
import com.practicas.Practicas.service.IRentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rents")
public class RentsController {
    @Autowired
    IRentsService rentsService;
    @Autowired
    IGrueService grueService;

    @PostMapping
    public ResponseEntity<Rent> rent(@RequestBody Rent rent) {
        try{
        return Optional.ofNullable(rent.getGrue().getId())
                .map(grueService::findBy)
                .filter(Grue::isAvailable)
                .map(grue -> {
                    rent.setUpdatedAt(null);
                    rent.setStatus(RentStatus.PENDING);
                    rentsService.create(rent);
                    grue.setAvailable(false);
                    grueService.edit(grue);
                    return new ResponseEntity<>(rent, HttpStatus.CREATED);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    @GetMapping("/{id}")
    public ResponseEntity<Rent> SearchOrder(@PathVariable(name = "id") long id) {
        try {
            if (rentsService.findBy(id) != null) {
                return new ResponseEntity<>(rentsService.findBy(id), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Rent>> FindAll() {
        try {
            return new ResponseEntity<>(rentsService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<Rent> editRent(@RequestBody Rent rent) {
        try {
            return Optional.ofNullable(rent.getGrue().getId())
                    .map(grueService::findBy)
                    .filter(Grue::isAvailable)
                    .filter(crane -> LocalDate.now().isBefore(rent.getStartDate().minusDays(1))
                            && rent.getStatus().equals(RentStatus.CONFIRMED))
                    .map(crane -> {
                        rent.setUpdatedAt(LocalDate.now());
                        rentsService.edit(rent);
                        crane.setAvailable(false);
                        grueService.edit(crane);
                        return new ResponseEntity<>(rent, HttpStatus.CREATED);
                    })
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    }


