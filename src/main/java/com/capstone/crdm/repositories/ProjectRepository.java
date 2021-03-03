package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.ProjectEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrdmRepository<ProjectEntity, Integer> {

}
