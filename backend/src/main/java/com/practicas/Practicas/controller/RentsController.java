package com.practicas.Practicas.controller;

import com.practicas.Practicas.model.ApiResponse;
import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.model.Rent;
import com.practicas.Practicas.model.dto.RentsActives;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/alquileres")
@CrossOrigin("http://localhost:4200")
public class RentsController {
    @Autowired
    IRentsService rentsService;
    @Autowired
    IGrueService grueService;

    @PostMapping
    public ResponseEntity<ApiResponse> addRent(@RequestBody Rent rent) {
        try {
            List<Rent> rentList = rentsService.findAll();
            ApiResponse response = new ApiResponse();

            for (Rent r : rentList) {
                if (r.getClient().equals(rent.getClient()) && r.getGrue().equals(rent.getGrue())) {
                    return new ResponseEntity<>(new ApiResponse(false, "Ya haz rentado esta grúa"), HttpStatus.OK);
                }
            }

            rentsService.create(rent);
            return new ResponseEntity<>(new ApiResponse(true, "Grúa rentada correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(new ApiResponse(false, "Error interno del servidor"), HttpStatus.INTERNAL_SERVER_ERROR);
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
    @GetMapping("/email/{email}")
    public ResponseEntity<List<Rent>> findRentByEmail(@PathVariable(name = "email") String email) {
        List<Rent> rents = rentsService.findAll().stream()
                .filter(rent -> rent.getClient().getEmail().equals(email))
                .collect(Collectors.toList());
        return new ResponseEntity<>(rents, HttpStatus.OK);
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
    @GetMapping("/rentsActives")
    public ResponseEntity <List<RentsActives>> findRentsActives(){
        try{
            return new ResponseEntity<>(rentsService.findRentsActives(),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteRent(@PathVariable(name = "id") long id) {
        try {
            Rent rent = rentsService.findBy(id);
            if (rent != null) {
                Grue grue = rent.getGrue();
                if (grue != null) {
                    grue.setAvailable(true);
                    grueService.edit(grue);
                }
                rentsService.deleteBy(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


