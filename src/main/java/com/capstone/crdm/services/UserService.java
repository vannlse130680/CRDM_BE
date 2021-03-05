package com.capstone.crdm.services;

import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends CrdmService<UserEntity, Integer, UserRepository> {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super(UserEntity.class);
        this.userRepository = userRepository;
    }

    @Override
    protected UserRepository getRepository() {
        return this.userRepository;
    }

//    public List<UserEntity> findUserByProjectId(int id) {
////        return userRepository.findUserAssignByProjectId(id);
//    }

}
