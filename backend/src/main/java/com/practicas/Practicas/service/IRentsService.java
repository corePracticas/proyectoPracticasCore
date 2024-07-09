package com.practicas.Practicas.service;

import com.practicas.Practicas.model.Rent;
import com.practicas.Practicas.model.dto.RentsActives;

import java.util.List;

public interface IRentsService extends ICRUD <Rent,Long> {
    List<RentsActives> findRentsActives();
}
