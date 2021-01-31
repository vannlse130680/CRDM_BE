package com.capstone.crdm.services;

import com.capstone.crdm.entities.ProjectAssign;
import com.capstone.crdm.repositories.ProjectAssignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectAssignServiceIml implements IProjectAssignService {

    private ProjectAssignRepository projectAssignRepository;

    @Autowired
    public ProjectAssignServiceIml(ProjectAssignRepository projectAssignRepository) {
        this.projectAssignRepository = projectAssignRepository;
    }

    @Override
    public Iterable<ProjectAssign> doProjectAssign(List<ProjectAssign> projectAssignList) {
        return projectAssignRepository.saveAll(projectAssignList);
    }
}
