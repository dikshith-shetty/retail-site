package com.project.retail_site.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.retail_site.entities.Product;
import com.project.retail_site.entities.User;
import com.project.retail_site.enums.CategoryEnum;
import com.project.retail_site.enums.RoleEnum;
import com.project.retail_site.services.impl.SimpleDiscountService;

@SpringBootTest(classes = { SimpleDiscountService.class})
class SimpleDiscountServiceTest {

    @Autowired
    private SimpleDiscountService discountService;

    @Test
    void testCalculateDiscount() {
        User user = new User();
        user.setRole(RoleEnum.EMPLOYEE);
        user.setRegistrationDate(LocalDate.now().minusYears(3));

        Product product1 = new Product();
        product1.setCategory(CategoryEnum.OTHER);
        product1.setPrice(200d);

        Product product2 = new Product();
        product2.setCategory(CategoryEnum.GROCERY);
        product2.setPrice(100d);

        List<Product> products = Arrays.asList(product1, product2);
        Double payableAmount = discountService.amountAfterDiscount(user, products);

        Assertions.assertEquals(230, payableAmount);
    }
}
