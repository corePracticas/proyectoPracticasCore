package com.practicas.Practicas.service.impl;

import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.repository.IGrueRepository;
import com.practicas.Practicas.repository.IGeneralRepository;
import com.practicas.Practicas.service.IGrueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GrueService extends ICRUDimpl <Grue,Long> implements IGrueService {
    @Autowired
    IGrueRepository grueRepository;
    @Override
    protected IGeneralRepository<Grue, Long> getRepo() {
        return grueRepository;
    }
}
