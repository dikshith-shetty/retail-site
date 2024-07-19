package com.project.retail_site.controllers;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.project.retail_site.controllers.request.BillRequest;
import com.project.retail_site.entities.Product;
import com.project.retail_site.entities.User;
import com.project.retail_site.enums.RoleEnum;
import com.project.retail_site.services.DiscountService;
import com.project.retail_site.services.ProductService;
import com.project.retail_site.services.UserService;
import com.project.retail_site.utils.TestUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.equalTo;

@WebMvcTest(BillingController.class)
class BillingControllerTest {
    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private DiscountService discountService;

    @MockBean
    private UserService userService;

    @MockBean
    private ProductService productService;
    

    @Test
    void calculateBillTest() throws Exception {
        User employeeUser = new User();
        employeeUser.setId(1l);
        employeeUser.setName("User Name");
        employeeUser.setRegistrationDate(LocalDate.now().minusDays(10));
        employeeUser.setRole(RoleEnum.EMPLOYEE);

        List<Product> products = new ArrayList<>(); 

        Double expectedValue = 120.00d;

        when(userService.getUserById(anyLong())).thenReturn(employeeUser);
        when(productService.getProductsByIds(anyList())).thenReturn(products);
        when(discountService.amountAfterDiscount(employeeUser, products)).thenReturn(expectedValue);
        
        BillRequest billRequest = new BillRequest();
        billRequest.setUserId(1l);
        billRequest.setProductIds(new ArrayList<>());
        this.mockMvc.perform(post("/billing/calculate")
                    .content(TestUtils.asJsonString(billRequest))
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(content().string(equalTo(expectedValue.toString())));

        
    }
    
}
