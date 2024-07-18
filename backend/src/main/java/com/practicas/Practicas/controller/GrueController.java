package com.practicas.Practicas.controller;

import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.service.IGrueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gruas")
@CrossOrigin("http://localhost:4200")
public class GrueController {
    @Autowired
    IGrueService grueService;

    @GetMapping
    public ResponseEntity <List<Grue>> findAll(){
        try {
            return new ResponseEntity<>(grueService.findAll(), HttpStatus.OK);
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
    @PutMapping
    public ResponseEntity <Grue> editCrane(@RequestBody Grue crane){
        try{
            if (grueService.findBy(crane.getId()) != null){
                return new ResponseEntity<>(grueService.edit(crane),HttpStatus.CREATED);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCrane(@PathVariable(name = "id") long id) {
        try {
            if (grueService.findBy(id) != null) {
                grueService.deleteBy(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
