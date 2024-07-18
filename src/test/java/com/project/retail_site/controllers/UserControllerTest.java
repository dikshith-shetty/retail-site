package com.project.retail_site.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.project.retail_site.entities.User;
import com.project.retail_site.enums.RoleEnum;
import com.project.retail_site.services.UserService;
import com.project.retail_site.utils.TestUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;


@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
	private MockMvc mockMvc;

    @MockBean
	private UserService userService;

    private static User testUser;
    
    @BeforeAll
    static void setUp() {
        testUser = new User();
        testUser.setName("TestUser");
        testUser.setId(12l);
        testUser.setRegistrationDate(LocalDate.now().minusYears(2).minusDays(1));
        testUser.setRole(RoleEnum.CUSTOMER);
    }

    @Test
    void createUserTest() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(testUser);
        this.mockMvc.perform(post("/users")
                    .content(TestUtils.asJsonString(testUser))
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(jsonPath("$.id", is(equalTo(testUser.getId())), Long.class))
                    .andExpect(jsonPath("$.name", is(testUser.getName())))
                    .andExpect(jsonPath("$.registrationDate", is(testUser.getRegistrationDate().toString())))
                    .andExpect(jsonPath("$.role", is(testUser.getRole().toString())));
    }


    @Test
    void getUserTest() throws Exception {
        when(userService.getUserById(anyLong())).thenReturn(testUser);
        this.mockMvc.perform(get("/users/{id}", 12))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id", is(equalTo(testUser.getId())), Long.class))
            .andExpect(jsonPath("$.name", is(testUser.getName())))
            .andExpect(jsonPath("$.registrationDate", is(testUser.getRegistrationDate().toString())))
            .andExpect(jsonPath("$.role", is(testUser.getRole().toString())));
    }


}
