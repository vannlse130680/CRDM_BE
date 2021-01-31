package com.capstone.crdm.services;

import com.capstone.crdm.entities.ProjectAssign;

import java.util.List;

public interface IProjectAssignService {
    Iterable<ProjectAssign> doProjectAssign(List<ProjectAssign> projectAssignList);
}
