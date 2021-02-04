package com.capstone.crdm.services;

import com.capstone.crdm.entities.Project;
import com.capstone.crdm.request.CreateProjectReq;

import java.util.List;

public interface IProjectService {
    List<Project> getAllProject();

    Project createProject(Project project);

    Project findProjectbyId(int id);

//    Project updateProject(int id, CreateProjectReq request);
}
