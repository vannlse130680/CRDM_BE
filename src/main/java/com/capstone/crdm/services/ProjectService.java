package com.capstone.crdm.services;

import com.capstone.crdm.constants.OperationMode;
import com.capstone.crdm.entities.ProjectEntity;
import com.capstone.crdm.repositories.ProjectAssignRepository;
import com.capstone.crdm.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ProjectService extends CrdmService<ProjectEntity, Integer, ProjectRepository> {

    private final ProjectRepository projectRepository;

    private final ProjectAssignRepository projectAssignRepository;

    public ProjectService(ProjectRepository projectRepository, ProjectAssignRepository projectAssignRepository) {
        super(ProjectEntity.class);
        this.projectRepository = projectRepository;
        this.projectAssignRepository = projectAssignRepository;
    }

    @Override
    protected ProjectRepository getRepository() {
        return this.projectRepository;
    }

    @Override
    protected void completeEntity(ProjectEntity entity, OperationMode operationMode) {
        super.completeEntity(entity, operationMode);
        entity.setCreatedAt(Instant.now());
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
