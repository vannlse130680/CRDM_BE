package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.Client;
import com.capstone.crdm.services.IClientService;
import com.capstone.crdm.utilities.CRDMMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ClientController {
    private final IClientService IClientService;


    @Autowired
    public ClientController(IClientService IClientService) {
        this.IClientService = IClientService;
    }

    @CrossOrigin
    @GetMapping("/client")
    // get all clients
    public ResponseEntity getAllClient(){
        try {
            List<Client> clientList = IClientService.getAllClient();

            if (clientList == null) {
                return new ResponseEntity("no client found", HttpStatus.OK);
            } else {
                return new ResponseEntity(clientList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(new CRDMMessage(e.getMessage()), HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PostMapping("/client")
    // create new client
    public ResponseEntity createClient(@RequestBody Client request){
        Client client = new Client();
        try {
            client.setName(request.getName());

            client = IClientService.createClient(client);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(new CRDMMessage(e.getMessage()), HttpStatus.CONFLICT);
        }
        return new ResponseEntity(client, HttpStatus.OK);
    }
}
