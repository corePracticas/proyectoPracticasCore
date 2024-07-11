package com.practicas.Practicas.service;

import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.model.dto.GrueWithCapacity;

import java.util.List;

public interface IGrueService extends ICRUD <Grue,Long> {
    List<GrueWithCapacity> grueCapacity(Integer capacity);
}
