package com.project.retail_site.services.impl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.project.retail_site.entities.Product;
import com.project.retail_site.exceptions.ProductNotFoundException;
import com.project.retail_site.repositories.ProductRepository;
import com.project.retail_site.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
    
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product == null) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductsByIds(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }
}

