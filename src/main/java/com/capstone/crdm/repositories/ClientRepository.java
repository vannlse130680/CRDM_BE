package com.capstone.crdm.repositories;

import com.capstone.crdm.entities.ClientEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrdmRepository<ClientEntity, Integer> {

}
