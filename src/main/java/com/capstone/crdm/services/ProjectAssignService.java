package com.capstone.crdm.services;

import com.capstone.crdm.entities.ProjectAssign;
import com.capstone.crdm.repositories.ProjectAssignRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectAssignService {

    private final ProjectAssignRepository projectAssignRepository;

    public ProjectAssignService(ProjectAssignRepository projectAssignRepository) {
        this.projectAssignRepository = projectAssignRepository;
    }

    public void doProjectAssign(List<ProjectAssign> projectAssignList) {
        projectAssignRepository.saveAll(projectAssignList);
    }

    public List<ProjectAssign> findProjectAssignByProId(int projectId) {
        return projectAssignRepository.findByProjectId(projectId);
    }

    public void deleteAllProjectAssignByProjectId(List<ProjectAssign> projectAssignList) {
        projectAssignRepository.deleteAll(projectAssignList);
    }

}
