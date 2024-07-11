package com.practicas.Practicas.controller;

import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.model.dto.GrueWithCapacity;
import com.practicas.Practicas.service.IGrueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grues")
public class GrueController {
    @Autowired
    IGrueService grueService;

    @PostMapping
    public ResponseEntity <Grue> createCrane(@RequestBody Grue grue) {
        try {
            if (grueService.findBy(grue.getId())!=null){
                grue.setAvailable(true);
                return new ResponseEntity<>(grueService.create(grue), HttpStatus.OK);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping
    public ResponseEntity <List<Grue>> findAll(){
        try {
            return new ResponseEntity<>(grueService.findAll(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }

    @PutMapping
    public ResponseEntity <Grue> editCrane(@RequestBody Grue crane){
        try{
        if (grueService.findBy(crane.getId()) != null){
            return new ResponseEntity<>(grueService.edit(crane),HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        }
    @GetMapping("/{id}")
    public ResponseEntity <Grue> findByID(@PathVariable(name = "id") long id) {
        try {
            if (grueService.findBy(id) != null) {
                return new ResponseEntity<>(grueService.findBy(id), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/capacity/{capacity}")
    public ResponseEntity<List<GrueWithCapacity>>GrueWithCapacity(@PathVariable(name="capacity")Integer capacity){
        try{
            if (grueService.grueCapacity(capacity).isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }else {
                return new ResponseEntity<>(grueService.grueCapacity(capacity),HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
