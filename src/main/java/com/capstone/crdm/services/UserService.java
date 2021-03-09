package com.capstone.crdm.services;

import com.capstone.crdm.constants.OperationMode;
import com.capstone.crdm.entities.UserEntity;
import com.capstone.crdm.repositories.UserRepository;
import com.capstone.crdm.security.authentication.CrdmRole;
import com.capstone.crdm.security.utils.AuthorizationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService extends CrdmService<UserEntity, Integer, UserRepository> {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Value("${crdm.security.password.default-password}")
    private String defaultPassword;

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
    public UserEntity create(UserEntity user) {
        user.setPassword(this.defaultPassword);
        return super.create(user);
    }

    @Override
    protected void completeEntity(UserEntity entity, OperationMode operationMode) {
        super.completeEntity(entity, operationMode);
        var encodedPassword = this.passwordEncoder.encode(entity.getPassword());
        entity.setPassword(encodedPassword);
    }

    public void updateProfile(UserEntity updatedProfile) {
        var currentProfile = this.findById(AuthorizationUtils.getCurrentUserId());
        currentProfile.setName(updatedProfile.getName());
        currentProfile.setDateOfBirth(updatedProfile.getDateOfBirth());
        currentProfile.setPhone(updatedProfile.getPhone());

        if (AuthorizationUtils.getCurrentUserRole().equals(CrdmRole.MANAGER.name())) {
            currentProfile.setRole(updatedProfile.getRole());
        }

        this.userRepository.save(currentProfile);
    }

    public void updatePassword(String newPassword) {
        var currentProfile = this.findById(AuthorizationUtils.getCurrentUserId());

        // we need password validation here (with some constrains)
        currentProfile.setPassword(this.passwordEncoder.encode(newPassword));

        this.userRepository.save(currentProfile);
    }

}
