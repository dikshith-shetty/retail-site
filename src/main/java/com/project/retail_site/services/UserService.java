package com.project.retail_site.services;

import com.project.retail_site.entities.User;

public interface UserService {
    
    public User getUserById(Long id);

    public User saveUser(User user);

}
