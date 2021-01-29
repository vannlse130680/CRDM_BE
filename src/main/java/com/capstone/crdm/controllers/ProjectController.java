package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.Client;
import com.capstone.crdm.entities.Project;
import com.capstone.crdm.services.IClientService;
import com.capstone.crdm.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ProjectController {
    IProjectService projectService;
    IClientService clientService;

    @Autowired
    public ProjectController(IProjectService projectService, IClientService clientService) {
        this.projectService = projectService;
        this.clientService = clientService;
    }





    @CrossOrigin
    @GetMapping("/project")
    public ResponseEntity getAllClient() {
        try {
            List<Project> projectList = projectService.getAllProject();

            if (projectList == null) {
                return new ResponseEntity("no client found", HttpStatus.OK);
            } else {
                return new ResponseEntity(projectList, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @CrossOrigin
    @PostMapping("/project")
    public ResponseEntity createProject(@RequestBody Project request) {
        Project project = new Project();
        try {


            project.setProduct(request.getProduct());
            project.setDeadline(request.getDeadline());
            project.setStatus(1);
            project.setRequirement(request.getRequirement());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            project.setCreatedDate(timestamp);
            project.setClientId(request.getClientId());
            

            Client client = clientService.getClientById(request.getClientId());

            project.setClient(client);
           project = projectService.createProject(project);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(project, HttpStatus.OK);
    }
}
