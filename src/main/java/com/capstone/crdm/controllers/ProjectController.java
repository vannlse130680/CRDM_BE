package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.ProjectAssign;
import com.capstone.crdm.entities.ProjectEntity;
import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.repositories.ProjectRepository;
import com.capstone.crdm.request.CreateProjectRequest;
import com.capstone.crdm.services.ClientService;
import com.capstone.crdm.services.CrdmService;
import com.capstone.crdm.services.ProjectAssignService;
import com.capstone.crdm.services.ProjectService;
import com.capstone.crdm.utilities.CRDMMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(path = "/project")
@RestController
public class ProjectController extends CrdmController<ProjectEntity, Integer, ProjectRepository> {

    private final ProjectService projectService;

    private final ClientService clientService;

    private final ProjectAssignService projectAssignService;

    public ProjectController(ProjectService projectService, ClientService clientService, ProjectAssignService projectAssignService) {
        this.projectService = projectService;
        this.clientService = clientService;
        this.projectAssignService = projectAssignService;
    }

    @GetMapping("/projectAssign/{id}")
    @Transactional
    // test cho update proejct with assign
    public ResponseEntity getAllProjectAssignByProjecId(@PathVariable("id") int id) {
        try {
            List<ProjectAssign> projectAssignList = projectAssignService.findProjectAssignByProId(id);

            if (projectAssignList == null) {
                return new ResponseEntity("no client found", HttpStatus.OK);
            } else {

                return new ResponseEntity(projectAssignList, HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/create-project")
    //Create new project
    public ResponseEntity create(@RequestBody CreateProjectRequest request) {
        ProjectEntity project = new ProjectEntity();
        try {
            var proj = request.getProject();

            //create first
            project = projectService.create(proj);

            //assign users
            List<UserEntity> userList = request.getUsers();

            List<ProjectAssign> projectAssignList = new ArrayList<>();
            for (UserEntity u : userList
            ) {
                ProjectAssign pa = new ProjectAssign();
                pa.setProjectId(project.getId());
                pa.setUserId(u.getId());
                projectAssignList.add(pa);
            }


//            List<ProjectAssign> projectAssignResult = (List<ProjectAssign>) projectAssignService.doProjectAssign(projectAssignList);
//            project.setProjectAssign(projectAssignResult);

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(project, HttpStatus.OK);
    }

    @PutMapping("/update-project")
    public ResponseEntity updateProject(@RequestBody CreateProjectRequest request) {
        ProjectEntity project = projectService.findById(request.getProject().getId());
        try {


            project.setProduct(request.getProject().getProduct());
            project.setDeadline(request.getProject().getDeadline());
            project.setStatus(request.getProject().getStatus());
            project.setRequirement(request.getProject().getRequirement());
//            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//            project.setCreatedDate(timestamp);
            project.setClientId(request.getProject().getClientId());


//            Client client = clientService.getClientById(request.getProject().getClientId());
//
//            project.setClient(client);
            project = projectService.create(project);

            //remove old
            List<ProjectAssign> projectAssignListOld = projectAssignService.findProjectAssignByProId(request.getProject().getId());
            projectAssignService.deleteAllProjectAssignByProjectId(projectAssignListOld);

            //add new
            List<UserEntity> userList = request.getUsers();

            List<ProjectAssign> projectAssignListNew = new ArrayList<>();
            for (UserEntity u : userList
            ) {
                ProjectAssign pa = new ProjectAssign();
                pa.setProjectId(project.getId());
                pa.setUserId(u.getId());
                projectAssignListNew.add(pa);
            }
//            System.out.println(projectAssignListNew);

            projectAssignService.doProjectAssign(projectAssignListNew);
//            System.out.println(project.getCreatedDate());
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(project, HttpStatus.OK);
    }

    @Override
    protected CrdmService<ProjectEntity, Integer, ProjectRepository> getService() {
        return this.projectService;
    }
}
