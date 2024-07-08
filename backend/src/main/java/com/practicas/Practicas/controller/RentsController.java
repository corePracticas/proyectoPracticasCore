package com.practicas.Practicas.controller;

import com.practicas.Practicas.model.Crane;
import com.practicas.Practicas.model.Rent;
import com.practicas.Practicas.service.ICraneService;
import com.practicas.Practicas.service.IRentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@RestController
@RequestMapping("/api/rents")
public class RentsController {
    @Autowired
    IRentsService rentsService;
    @Autowired
    ICraneService craneService;

    @PostMapping
    public ResponseEntity<Rent> rent(@RequestBody Rent rent) {
        return Optional.ofNullable(rent.getCrane().getId())
                .map(craneService::findBy)
                .filter(Crane::getAvailable)
                .map(crane -> {
                    rent.setCreated(LocalDate.now());
                    rent.setUpdated(null);
                    rentsService.create(rent);
                    crane.setAvailable(false);
                    craneService.edit(crane);
                    return new ResponseEntity<>(rent, HttpStatus.CREATED);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rent> SearchOrder(@PathVariable(name = "id") long id) {
        if (rentsService.findBy(id) != null) {
            return new ResponseEntity<>(rentsService.findBy(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<Rent>> FindAll(){
        return new ResponseEntity<>(rentsService.findAll(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Rent> editRent (@RequestBody Rent rent){
       return Optional.ofNullable(rent.getCrane().getId())
               .map(craneService::findBy)
               .filter(Crane::getAvailable)
               .filter(crane -> LocalDate.now().isBefore(rent.getCreated().minusDays(1)))
               .map(crane -> {
                   rent.setUpdated(LocalDate.now());
                   rentsService.edit(rent);
                   crane.setAvailable(false);
                   craneService.edit(crane);
                   return new ResponseEntity<>(rent, HttpStatus.CREATED);
               })
               .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    }


