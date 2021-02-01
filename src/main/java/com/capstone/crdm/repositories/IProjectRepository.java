package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository extends JpaRepository<Project, Integer> {
    @Override
    List<Project> findAll();

    @Override
    <S extends Project> S save(S s);

    @Override
    Optional<Project> findById(Integer integer);
}
