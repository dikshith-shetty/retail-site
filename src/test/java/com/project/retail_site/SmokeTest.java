package com.project.retail_site;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.retail_site.controllers.UserController;

@SpringBootTest
class SmokeTest {
    
    @Autowired
    private UserController userController;

    @Test
    void testController() {
        Assertions.assertNotNull(userController);
    }

}
