package com.capstone.crdm.services;

import com.capstone.crdm.entities.Project;
import com.capstone.crdm.entities.User;
import com.capstone.crdm.repositories.IProjectRepository;
import com.capstone.crdm.repositories.ProjectAssignRepository;
import com.capstone.crdm.request.CreateProjectReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceIml implements IProjectService {
    private IProjectRepository projectRepository;
    private ProjectAssignRepository projectAssignRepository;

    public ProjectServiceIml(IProjectRepository projectRepository, ProjectAssignRepository projectAssignRepository) {
        this.projectRepository = projectRepository;
        this.projectAssignRepository = projectAssignRepository;
    }

    @Autowired

    @Override
    public List<Project> getAllProject() {
        return  projectRepository.findAll();
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public Project findProjectbyId(int id) {
        return projectRepository.findById(id).get();
    }



//    @Override
//    public Project updateProject(int id, Project project, List<User> userList) {
//        Project result  = projectRepository.findById(id).get();
//        result.setClientId(project.getClientId());
//        result.setProduct(project.getProduct());
//        result.setDeadline(project.getDeadline());
//        result.setRequirement(project.getRequirement());
//        result.setStatus(project.getStatus());
//        projectRepository.
//        return null;
//    }
}
