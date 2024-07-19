package com.project.retail_site;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;


@SpringBootTest
@AutoConfigureMockMvc
class MvcLayerTest {

    @Autowired
	private MockMvc mockMvc;

    @Test
	void healthCheck() throws Exception {
		this.mockMvc.perform(get("/actuator/health")).andExpect(status().isOk())
				.andExpect(content().string(containsString("UP")));
	}

	@Test
	void urlNotFoundTest() throws Exception {
		String invalidUrl = "invalid-url";
		this.mockMvc.perform(get("/"+ invalidUrl)).andExpect(status().isNotFound())
				.andExpect(content().string(containsString(invalidUrl)));
	}
}
