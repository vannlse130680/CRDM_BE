package com.capstone.crdm.services;

import com.capstone.crdm.entities.ProjectAssign;
import com.capstone.crdm.repositories.ProjectAssignRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.REQUIRES_NEW)
@Service
public class ProjectAssignService {

    private final ProjectAssignRepository projectAssignRepository;

    public ProjectAssignService(ProjectAssignRepository projectAssignRepository) {
        this.projectAssignRepository = projectAssignRepository;
    }

    public void doProjectAssign(List<ProjectAssign> projectAssignList) {

        projectAssignRepository.saveAll(projectAssignList);
    }

    public List<ProjectAssign> findProjectAssignments(int projectId) {
        return projectAssignRepository.findByProjectId(projectId);
    }

    public void deleteByIds(List<Integer> ids) {
        projectAssignRepository.deleteByIds(ids);
    }

}
