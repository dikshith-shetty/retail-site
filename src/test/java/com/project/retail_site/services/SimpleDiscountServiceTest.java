package com.project.retail_site.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

    private static Product product1;
    private static Product product2;
    private static List<Product> products;

    @BeforeAll
    static void setUp() {
        product1 = new Product();
        product1.setCategory(CategoryEnum.OTHER);
        product1.setPrice(200d);

        product2 = new Product();
        product2.setCategory(CategoryEnum.GROCERY);
        product2.setPrice(100d);

        products = Arrays.asList(product1, product2);
    }



    @Test
    void testCalculateDiscount1() {
        User user = new User();
        user.setRole(RoleEnum.EMPLOYEE);
        user.setRegistrationDate(LocalDate.now().minusYears(3));

        Double payableAmount = discountService.amountAfterDiscount(user, products);

        Assertions.assertEquals(230, payableAmount);
    }

    @Test
    void testCalculateDiscount2() {
        User user = new User();
        user.setRole(RoleEnum.AFFILIATE);
        user.setRegistrationDate(LocalDate.now().minusYears(3));

        Double payableAmount = discountService.amountAfterDiscount(user, products);

        Assertions.assertEquals(270, payableAmount);
    }

    @Test
    void testCalculateDiscount3() {
        User user = new User();
        user.setRole(RoleEnum.CUSTOMER);
        user.setRegistrationDate(LocalDate.now().minusYears(2).minusDays(1));

        Double payableAmount = discountService.amountAfterDiscount(user, products);

        Assertions.assertEquals(280, payableAmount);
    }

    @Test
    void testCalculateDiscount4() {
        User user = new User();
        user.setRole(RoleEnum.CUSTOMER);
        user.setRegistrationDate(LocalDate.now().minusYears(2));

        Double payableAmount = discountService.amountAfterDiscount(user, products);

        Assertions.assertEquals(285, payableAmount);
    }
}
