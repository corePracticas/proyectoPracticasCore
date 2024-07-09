package com.practicas.Practicas.service.impl;

import com.practicas.Practicas.model.Client;
import com.practicas.Practicas.repository.IClientsRepository;
import com.practicas.Practicas.repository.IGeneralRepository;
import com.practicas.Practicas.service.IClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientsService extends ICRUDimpl <Client,Long> implements IClientsService {
    @Autowired
    IClientsRepository clientsRepository;

    @Override
    protected IGeneralRepository<Client, Long> getRepo() {
        return clientsRepository;
    }


    @Override
    @Query("SELECT c FROM Client c WHERE c.email = :email")
    public Client findByEmail(@Param("email") String email) {
        return clientsRepository.findByEmail(email);
    }

}
