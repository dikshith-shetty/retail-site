package com.project.retail_site.services.impl;

import org.springframework.stereotype.Service;

import com.project.retail_site.entities.User;
import com.project.retail_site.exceptions.UserNotFoundException;
import com.project.retail_site.repositories.UserRepository;
import com.project.retail_site.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
