package com.capstone.crdm.services;

import com.capstone.crdm.entities.Project;
import com.capstone.crdm.repositories.IProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceIml implements IProjectService {
    private IProjectRepository projectRepository;

    public ProjectServiceIml(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
}
