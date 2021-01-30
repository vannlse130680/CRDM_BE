package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.Client;
import com.capstone.crdm.entities.Project;
import com.capstone.crdm.entities.ProjectAssign;
import com.capstone.crdm.entities.User;
import com.capstone.crdm.request.CreateProjectReq;
import com.capstone.crdm.services.IClientService;
import com.capstone.crdm.services.IProjectAssignService;
import com.capstone.crdm.services.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ProjectController {
    IProjectService projectService;
    IClientService clientService;
    IProjectAssignService projectAssignService;

    @Autowired
    public ProjectController(IProjectService projectService, IClientService clientService, IProjectAssignService projectAssignService) {
        this.projectService = projectService;
        this.clientService = clientService;
        this.projectAssignService = projectAssignService;
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
    @Transactional
    public ResponseEntity createProject(@RequestBody CreateProjectReq request) {
        Project project = new Project();
        try {


            project.setProduct(request.getProject().getProduct());
            project.setDeadline(request.getProject().getDeadline());
            project.setStatus(1);
            project.setRequirement(request.getProject().getRequirement());
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            project.setCreatedDate(timestamp);
            project.setClientId(request.getProject().getClientId());


            Client client = clientService.getClientById(request.getProject().getClientId());

            project.setClient(client);
            project = projectService.createProject(project);
            List<User> userList = request.getUsers();

            List<ProjectAssign> projectAssignList = new ArrayList<>();
            for (User u : userList
            ) {
                ProjectAssign pa = new ProjectAssign();
                pa.setProjectId(project.getId());
                pa.setUserId(u.getId());
                projectAssignList.add(pa);
            }
            System.out.println(projectAssignList);

            projectAssignService.doProjectAssign(projectAssignList);
            System.out.println(project.getCreatedDate());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(project, HttpStatus.OK);
    }
}
