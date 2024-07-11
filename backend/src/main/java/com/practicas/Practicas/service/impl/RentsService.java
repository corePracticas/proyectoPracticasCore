package com.practicas.Practicas.service.impl;

import com.practicas.Practicas.model.Rent;
import com.practicas.Practicas.model.dto.RentStatus;
import com.practicas.Practicas.model.dto.RentsActives;
import com.practicas.Practicas.repository.IGeneralRepository;
import com.practicas.Practicas.repository.IRentsRepository;
import com.practicas.Practicas.service.IRentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class RentsService extends ICRUDimpl <Rent,Long> implements IRentsService {
    @Autowired
    IRentsRepository rentsRepository;

    @Override
    protected IGeneralRepository<Rent, Long> getRepo() {
        return rentsRepository;
    }

    @Override
    public List<RentsActives> findRentsActives() {
        return rentsRepository.rentsActives();
    }

    @Override
    public List<RentStatus> findRentStatus(String status) {
        return rentsRepository.rentStatus(status);
    }


}
