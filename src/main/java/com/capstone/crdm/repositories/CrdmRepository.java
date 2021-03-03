package com.capstone.crdm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface CrdmRepository<E, ID> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {

}
