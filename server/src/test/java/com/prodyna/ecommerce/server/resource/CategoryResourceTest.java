package com.prodyna.ecommerce.server.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.prodyna.ecommerce.services.CategoryService;
import com.prodyna.ecommerce.web.CategoryResource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CategoryResourceTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ConversionService conversionService;

	@MockBean
	private CategoryService categoryService;

	private MockMvc mockMvc;

	@Before
	public void setupMvcMock() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testDelete() throws Exception {
		String id = "a3232";
		mockMvc.perform(
				delete(CategoryResource.createLink().toString() + "/" + id).accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNoContent());
	}

}
