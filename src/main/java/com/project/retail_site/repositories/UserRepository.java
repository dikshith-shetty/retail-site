package com.project.retail_site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.retail_site.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
}

