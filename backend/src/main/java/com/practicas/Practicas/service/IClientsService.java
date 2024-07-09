package com.practicas.Practicas.service;

import com.practicas.Practicas.model.Client;

public interface IClientsService extends ICRUD <Client,Long>{
    Client findByEmail(String email);
}
