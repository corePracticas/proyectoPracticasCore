package com.practicas.Practicas.service.impl;

import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.model.dto.GrueWithCapacity;
import com.practicas.Practicas.repository.IGrueRepository;
import com.practicas.Practicas.repository.IGeneralRepository;
import com.practicas.Practicas.service.IGrueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrueService extends ICRUDimpl <Grue,Long> implements IGrueService {
    @Autowired
    IGrueRepository grueRepository;
    @Override
    protected IGeneralRepository<Grue, Long> getRepo() {
        return grueRepository;
    }

    @Override
    public List<GrueWithCapacity> grueCapacity(Integer capacity) {
        return grueRepository.grueCapacity(capacity);
    }
}
