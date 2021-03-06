package com.capstone.crdm.services;

import com.capstone.crdm.constants.OperationMode;
import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.exception.CrdmIllegalStateException;
import com.capstone.crdm.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends CrdmService<UserEntity, Integer, UserRepository> {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(UserEntity.class);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected UserRepository getRepository() {
        return this.userRepository;
    }

    @Override
    protected void completeEntity(UserEntity entity, OperationMode operationMode) {
        super.completeEntity(entity, operationMode);
        var encodedPassword = this.passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);
    }
}
