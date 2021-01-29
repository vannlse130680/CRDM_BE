package com.capstone.crdm.services;

import com.capstone.crdm.entities.Client;
import com.capstone.crdm.repositories.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceIml implements IClientService {

    private IClientRepository IClientRepository;

    @Autowired
    public ClientServiceIml(IClientRepository IClientRepository) {
        this.IClientRepository = IClientRepository;
    }

    @Override
    public List<Client> getAllClient() {
        return IClientRepository.findAll();
    }

    @Override
    public Client createClient(Client client) {
        client =  IClientRepository.saveAndFlush(client);

        return client;

    }

    @Override
    public Client getClientById(int id) {
        return IClientRepository.findById(id).get();
    }
}
