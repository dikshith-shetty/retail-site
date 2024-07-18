package com.project.retail_site.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.retail_site.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
