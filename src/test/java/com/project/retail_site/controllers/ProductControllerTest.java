package com.project.retail_site.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.project.retail_site.entities.Product;
import com.project.retail_site.entities.User;
import com.project.retail_site.enums.CategoryEnum;
import com.project.retail_site.services.ProductService;
import com.project.retail_site.utils.TestUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
	private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private static Product testProduct;

    @BeforeAll
    static void setUp() {
        testProduct = new Product();
        testProduct.setId(1l);
        testProduct.setName("Product1");
        testProduct.setPrice(200.00d);
        testProduct.setCategory(CategoryEnum.OTHER);
    }

    @Test
    void getProductTest() throws Exception {
        when(productService.getProductById(anyLong())).thenReturn(testProduct);
        this.mockMvc.perform(get("/products/{id}", 1))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id", is(equalTo(testProduct.getId())), Long.class))
            .andExpect(jsonPath("$.name", is(testProduct.getName())))
            .andExpect(jsonPath("$.category", is(testProduct.getCategory().toString())));
    }

    @Test
    void createProductTest() throws Exception {
        when(productService.saveProduct(any(Product.class))).thenReturn(testProduct);
        this.mockMvc.perform(post("/products")
                    .content(TestUtils.asJsonString(testProduct))
	                .contentType(MediaType.APPLICATION_JSON)
	                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(jsonPath("$.id", is(equalTo(testProduct.getId())), Long.class))
                    .andExpect(jsonPath("$.name", is(testProduct.getName())))
                    .andExpect(jsonPath("$.category", is(testProduct.getCategory().toString())));
    }


}


