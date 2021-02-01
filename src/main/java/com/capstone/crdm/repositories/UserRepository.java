package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Override
    List<User> findAll();

    @Query("SELECT u FROM User u WHERE (u.id IN (SELECT pa.userId FROM ProjectAssign pa WHERE pa.projectId = :projectId))")
    List<User> findUserAssignByProjectId(@Param("projectId") Integer projectId);
}
