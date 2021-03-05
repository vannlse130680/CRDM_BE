package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrdmRepository<UserEntity, Integer> {
//
//    @Query("SELECT u FROM User u WHERE (u.id IN (SELECT pa.userId FROM ProjectAssign pa WHERE pa.projectId = :projectId))")
//    List<UserEntity> findUserAssignByProjectId(@Param("projectId") Integer projectId);

    Optional<UserEntity> findByUsername(String username);

}
