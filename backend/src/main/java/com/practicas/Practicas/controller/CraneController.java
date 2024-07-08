package com.practicas.Practicas.controller;

import com.practicas.Practicas.model.Crane;
import com.practicas.Practicas.model.Rent;
import com.practicas.Practicas.service.ICraneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/craners")
public class CraneController {
    @Autowired
    ICraneService craneService;

    @PostMapping
    public ResponseEntity <Crane> createCraner(@RequestBody Crane crane){
        crane.setAvailable(true);
        return new ResponseEntity<>(craneService.create(crane), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity <List<Crane>> findAll(){
        return new ResponseEntity<>(craneService.findAll(),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity <Crane> editCraner(@RequestBody Crane crane){
        if (craneService.findBy(crane.getId()) != null){
            return new ResponseEntity<>(craneService.edit(crane),HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity <Crane> findByID(@PathVariable(name = "id") long id){
        if (craneService.findBy(id)!=null){
            return new ResponseEntity<>(craneService.findBy(id),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
