package com.capstone.crdm.services;

import com.capstone.crdm.entities.ClientEntity;
import com.capstone.crdm.repositories.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends CrdmService<ClientEntity, Integer, ClientRepository> {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository IClientRepository) {
        super(ClientEntity.class);
        this.clientRepository = IClientRepository;
    }

    @Override
    protected ClientRepository getRepository() {
        return this.clientRepository;
    }
}
