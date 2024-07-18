package com.project.retail_site.services;

import java.util.List;

import com.project.retail_site.entities.Product;

public interface ProductService {

    public Product getProductById(Long id);

    public Product saveProduct(Product product);

    public List<Product> getProductsByIds(List<Long> productIds);

}
