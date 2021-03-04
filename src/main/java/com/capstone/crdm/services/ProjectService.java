package com.capstone.crdm.services;

import com.capstone.crdm.constants.OperationMode;
import com.capstone.crdm.entities.ProjectAssign;
import com.capstone.crdm.entities.ProjectEntity;
import com.capstone.crdm.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProjectService extends CrdmService<ProjectEntity, Integer, ProjectRepository> {

    private final ProjectRepository projectRepository;

    private final ProjectAssignService projectAssignService;

    public ProjectService(ProjectRepository projectRepository, ProjectAssignService projectAssignService) {
        super(ProjectEntity.class);
        this.projectRepository = projectRepository;
        this.projectAssignService = projectAssignService;
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

    public void doProjectAssign(List<ProjectAssign> projectAssignList) {
        this.projectAssignService.doProjectAssign(projectAssignList);
    }

    public List<ProjectAssign> findProjectAssignments(int projectId) {
        return this.projectAssignService.findProjectAssignments(projectId);
    }

    public void removeProjectAssignments(Integer id, List<Integer> assignmentIds) {
        this.findById(id);
        this.projectAssignService.deleteByIds(assignmentIds);
    }

}
