package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.ProjectAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectAssignRepository extends JpaRepository<ProjectAssign, Integer> {

    List<ProjectAssign> findByProjectId(int projectId);

    void deleteByIds(List<Integer> ids);

}
