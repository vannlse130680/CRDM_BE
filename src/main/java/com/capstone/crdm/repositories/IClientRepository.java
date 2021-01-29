package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface IClientRepository extends JpaRepository<Client, Integer> {
    @Override
    List<Client> findAll();

    @Override
    <S extends Client> S save(S s);

    @Override
    Optional<Client> findById(Integer id);
}
