package com.project.retail_site;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;


/*
 * Sanity test
 */

@SpringBootTest
class RetailSiteApplicationTest {
	
	@Autowired
    ApplicationContext applicationContext;
	
	@Test
	void contextLoads() {
		Assertions.assertNotNull(applicationContext);
	}
 
}