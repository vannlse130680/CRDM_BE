package com.capstone.crdm.controllers;

import com.capstone.crdm.entities.ProjectAssign;
import com.capstone.crdm.entities.ProjectEntity;
import com.capstone.crdm.repositories.ProjectRepository;
import com.capstone.crdm.services.CrdmService;
import com.capstone.crdm.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping(path = "/project")
@RestController
public class ProjectController extends CrdmController<ProjectEntity, Integer, ProjectRepository> {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/{id}/project-assignments")
    public ResponseEntity<URI> assignMember(@PathVariable("id") int id, @RequestBody List<ProjectAssign> assignments) {
        this.projectService.doProjectAssign(assignments);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/project-assignments")
    public ResponseEntity<List<ProjectAssign>> findProjectAssignments(@PathVariable("id") int id) {
        return ResponseEntity.ok(this.projectService.findProjectAssignments(id));
    }

    @DeleteMapping("/{id}/project-assignments")
    public ResponseEntity<List<ProjectAssign>> getAllProjectAssignByProjecId(@PathVariable("id") int id, List<Integer> ids) {
        this.projectService.removeProjectAssignments(id, ids);
        return ResponseEntity.ok().build();
    }

    @Override
    protected CrdmService<ProjectEntity, Integer, ProjectRepository> getService() {
        return this.projectService;
    }

}
