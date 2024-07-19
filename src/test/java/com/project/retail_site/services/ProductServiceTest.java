package com.project.retail_site.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.project.retail_site.entities.Product;
import com.project.retail_site.enums.CategoryEnum;
import com.project.retail_site.exceptions.ProductNotFoundException;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService productService;


    @Test
    void saveProductTest() {
        Product p = new Product();
        p.setName("Product1");
        p.setCategory(CategoryEnum.GROCERY);
        p.setPrice(223.20d);
        Product rP = productService.saveProduct(p);
        Assertions.assertNotNull(rP.getId());
    }

    @Test
    void getProductByIdTest() {
        Product p = new Product();
        p.setName("Product2");
        p.setCategory(CategoryEnum.OTHER);
        p.setPrice(203.20d);
        Product pp = productService.saveProduct(p);

        Product rp = productService.getProductById(pp.getId());

        Assertions.assertEquals(pp, rp);
    }

    @Test
    void getProductByIdTests() {
        Product p = new Product();
        p.setName("Product3");
        p.setCategory(CategoryEnum.GROCERY);
        p.setPrice(223.20d);
        Product rP = productService.saveProduct(p);
        p = new Product();
        p.setName("Product4");
        p.setCategory(CategoryEnum.OTHER);
        p.setPrice(333.20d);
        Product pp = productService.saveProduct(p);

        List<Long> ids = new ArrayList<>();
        ids.add(rP.getId());
        ids.add(pp.getId());
        List<Product> result = productService.getProductsByIds(new ArrayList<>(ids));
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.stream().anyMatch(e -> e.getId() == rP.getId()));
        Assertions.assertTrue(result.stream().anyMatch(e -> e.getId() == pp.getId()));
    }

    @Test
    void getProductByIdTestForNull() {
        Assertions.assertThrowsExactly( ProductNotFoundException.class, () -> productService.getProductById(2344453l));
    }
}
