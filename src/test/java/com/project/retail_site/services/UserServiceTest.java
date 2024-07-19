package com.project.retail_site.services;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.retail_site.entities.User;
import com.project.retail_site.enums.RoleEnum;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;


    @Test
    void saveProductTest() {
        User u = new User();
        u.setName("User1");
        u.setRole(RoleEnum.CUSTOMER);
        u.setRegistrationDate(LocalDate.now().minusYears(3));
        User rU = userService.saveUser(u);
        Assertions.assertNotNull(rU.getId());
    }

    @Test
    void getProductByIdTest() {
        User user = new User();
        user.setName("User2");
        user.setRole(RoleEnum.AFFILIATE);
        user.setRegistrationDate(LocalDate.now().minusMonths(6));
        User uu = userService.saveUser(user);
        User ru = userService.getUserById(uu.getId());
        Assertions.assertEquals(uu, ru);
    }
}
