package com.practicas.Practicas.repository;

import com.practicas.Practicas.model.Rent;
import com.practicas.Practicas.model.dto.RentsActives;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRentsRepository extends IGeneralRepository <Rent,Long> {
    @Query("SELECT NEW com.practicas.Practicas.model.dto.RentsActives(r.id, r.startDate, r.endDate, r.totalPrice, r.status, r.createdAt, r.updatedAt, g.id, c.id, c.name) " +
            "FROM Rent r " +
            "JOIN r.grue g " +
            "JOIN r.client c " +
            "WHERE r.endDate < CURRENT_DATE")
    List<RentsActives> rentsActives();
}
