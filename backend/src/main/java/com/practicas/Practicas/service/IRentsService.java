package com.practicas.Practicas.service;

import com.practicas.Practicas.model.Rent;
import com.practicas.Practicas.model.dto.RentStatus;
import com.practicas.Practicas.model.dto.RentsActives;

import java.util.List;

public interface IRentsService extends ICRUD <Rent,Long> {
    List<RentsActives> findRentsActives();
    List<RentStatus>findRentStatus(String status);
}
