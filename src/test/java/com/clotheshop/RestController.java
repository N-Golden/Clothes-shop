package com.clotheshop;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.clotheshop.entities.Category;
import com.clotheshop.entities.Product;
import com.clotheshop.service.impl.CategoryService;
import com.clotheshop.service.impl.ProductService;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class RestController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoryService service;

	@MockBean
	private ProductService prodService;

	private Product product;

	@Before
	public void setup() {
		product = new Product(100L, "ptest", "2", "imageTest");
	}

	@After
	public void tearDown() {
		product = null;
	}

	@Test
	public void indexTest() throws Exception {
		List<Category> list = new ArrayList<>();
		list.add(new Category("cate1"));
		list.add(new Category("cate2"));
		Mockito.when(service.findAll()).thenReturn(list);
		mockMvc.perform(get("/")).andExpect(status().isOk());
	}

	@Test
	public void deleteProductTest() throws Exception {
		mockMvc.perform(delete("/api/product")
				.contentType(MediaType.APPLICATION_JSON) // request content type
		        .content("[1]") // request body contain json data
		        .accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andDo(print());
	}
	
	@Test
	public void addProductTest() throws Exception {
	    MockMultipartFile file = new MockMultipartFile("file", 
	            "hello.png", 
	            MediaType.IMAGE_PNG_VALUE, 
	            "Hello, World!".getBytes());
	    
		Mockito.when(prodService.addAndUpdateOne(product)).thenReturn(product);
		mockMvc.perform(multipart("/api/product/post")
				.file(file)
				.param("name", product.getName())
				.param("price", String.valueOf(product.getPrice()))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	public void updateProductTest() throws Exception {
	    MockMultipartFile file = new MockMultipartFile("file", 
	            "hello.txt", 
	            MediaType.IMAGE_PNG_VALUE, 
	            "Hello, World!".getBytes());
	
	    Mockito.when(prodService.findOne(product.getId())).thenReturn(new Product(product.getId()));
		Mockito.when(prodService.addAndUpdateOne(product)).thenReturn(product);
	    
		mockMvc.perform(multipart("/api/product/update")
				.file(file)
				.param("name", product.getName())
				.param("price", String.valueOf(product.getPrice()))
				.param("id", String.valueOf(product.getId()))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andDo(print());
	}
}
