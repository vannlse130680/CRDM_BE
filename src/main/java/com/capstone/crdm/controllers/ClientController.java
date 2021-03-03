package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.ClientEntity;
import com.capstone.crdm.repositories.ClientRepository;
import com.capstone.crdm.services.ClientService;
import com.capstone.crdm.services.CrdmService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/clients")
@RestController
public class ClientController extends CrdmController<ClientEntity, Integer, ClientRepository> {

    private final ClientService clientService;

    public ClientController(ClientService IClientService) {
        this.clientService = IClientService;
    }

    @Override
    protected CrdmService<ClientEntity, Integer, ClientRepository> getService() {
        return this.clientService;
    }

}
