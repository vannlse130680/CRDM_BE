package com.capstone.crdm.services;

import com.capstone.crdm.entities.User;
import com.capstone.crdm.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIml implements IUserService {
    private UserRepository userRepository;
    @Autowired
    public UserServiceIml(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUserByProjectId(int id) {
        return userRepository.findUserAssignByProjectId(id);
    }
}
