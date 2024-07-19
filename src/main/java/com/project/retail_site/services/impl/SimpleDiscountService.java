package com.project.retail_site.services.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.project.retail_site.entities.Product;
import com.project.retail_site.entities.User;
import com.project.retail_site.enums.CategoryEnum;
import com.project.retail_site.enums.RoleEnum;
import com.project.retail_site.services.DiscountService;

@Primary
@Service
public class SimpleDiscountService implements DiscountService {
    public Double amountAfterDiscount(User user, List<Product> products) {
        Double total = products.stream().map(Product::getPrice).reduce(0d, (subtotal, element) -> subtotal + element);
        Double discount = 0d;

        Double discountableAmount = products.stream()
                                        .filter(product -> product.getCategory() != CategoryEnum.GROCERY)
                                        .map(Product::getPrice)
                                        .reduce(0d, (subtotal, element) -> subtotal + element);

        if (RoleEnum.EMPLOYEE == user.getRole()) {
            discount = discountableAmount * 0.30;
        } else if (RoleEnum.AFFILIATE == user.getRole()) {
            discount = discountableAmount * 0.10;
        } else if (user.getRegistrationDate().isBefore(LocalDate.now().minusYears(2))) {
            discount = discountableAmount * 0.05;
        }
        total = (total - discount);
        Double additionalDiscount = Math.floor(total/100) * 5;
        total = (total - additionalDiscount);
        return total;
    }
}

