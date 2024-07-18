package com.project.retail_site.services;

import java.util.List;

import com.project.retail_site.entities.Product;
import com.project.retail_site.entities.User;

public interface DiscountService {
     public Double amountAfterDiscount(User user, List<Product> products);
}
