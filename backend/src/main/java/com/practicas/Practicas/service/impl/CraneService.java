package com.practicas.Practicas.service.impl;

import com.practicas.Practicas.model.Crane;
import com.practicas.Practicas.repository.ICraneRepository;
import com.practicas.Practicas.repository.IGeneralRepository;
import com.practicas.Practicas.service.ICraneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CraneService extends ICRUDimpl <Crane,Long> implements ICraneService {
    @Autowired
    ICraneRepository craneRepository;
    @Override
    protected IGeneralRepository<Crane, Long> getRepo() {
        return craneRepository;
    }
}
