package com.practicas.Practicas.repository;

import com.practicas.Practicas.model.Grue;
import com.practicas.Practicas.model.dto.GrueWithCapacity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IGrueRepository extends IGeneralRepository <Grue,Long> {
    @Query("SELECT NEW com.practicas.Practicas.model.dto.GrueWithCapacity(g.id, g.name, g.type, g.capacity, g.location, " +
            "g.available, g.pricePerMonth, g.createdAt, g.updatedAt) FROM Grue g " +
            "WHERE g.capacity= :capacity")
    List<GrueWithCapacity> grueCapacity(@Param("capacity")Integer capacity);

}
