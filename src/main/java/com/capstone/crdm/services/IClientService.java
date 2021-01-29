package com.capstone.crdm.services;

import com.capstone.crdm.entities.Client;

import java.util.List;

public interface IClientService {
    List<Client> getAllClient();

    Client createClient(Client client);

    Client getClientById(int id);
}
