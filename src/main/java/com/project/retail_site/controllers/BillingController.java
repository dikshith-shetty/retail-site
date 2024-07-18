package com.project.retail_site.controllers;


import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.retail_site.controllers.request.BillRequest;
import com.project.retail_site.entities.Product;
import com.project.retail_site.entities.User;
import com.project.retail_site.services.UserService;
import com.project.retail_site.services.DiscountService;
import com.project.retail_site.services.ProductService;

@RestController
@RequestMapping("/billing")
public class BillingController {

    private DiscountService discountService;
    private UserService userService;
    private ProductService productService;

    public BillingController(ProductService productService, UserService userService, DiscountService discountCalculator) {
        this.productService = productService;
        this.userService = userService;
        this.discountService = discountCalculator;
    }

    @PostMapping("/calculate")
    public Double calculateBill(@RequestBody BillRequest billRequest) {
        User user = userService.getUserById(billRequest.getUserId());
        List<Product> products = productService.getProductsByIds(billRequest.getProductIds());
        return discountService.amountAfterDiscount(user, products);
    }
}
