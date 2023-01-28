package com.clotheshop;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.clotheshop.entities.Category;
import com.clotheshop.entities.Product;
import com.clotheshop.repository.CategoryRepository;
import com.clotheshop.repository.ProductRepository;
import com.clotheshop.service.IProductService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductService {

	@Autowired
	private IProductService service;

	@MockBean
	private ProductRepository productRepo;

	@MockBean
	private CategoryRepository categoryRepo;

	private Product p1;
	private Product p2;
	private ArrayList<Product> lists = new ArrayList<>();

	@Before
	public void setup() {
		p1 = new Product("p1", "linkImg1", "1");
		p2 = new Product("p2", "linkImg2", "2");
		lists.add(p1);
		lists.add(p2);
	}

	@After
	public void tearDown() {
		p1 = null;
		p2 = null;
		lists.clear();
	}

	@Test
	public void findAllTest() {
		Mockito.when(productRepo.findAll()).thenReturn(lists);
		Assert.assertEquals(2, service.findAll().size());
	}

	@Test
	public void addAndUpdateOneTest() {
		Mockito.when(productRepo.save(p1)).thenReturn(p1);
		Assert.assertNotNull(service.addAndUpdateOne(p1));
	}

	@Test
	public void findPaginationTest() {
		Long id = 1L;
		Integer page = 2;
		Integer size = 3;

		Category cate = new Category();
		Mockito.when(categoryRepo.findById(id)).thenReturn(Optional.of(cate));
		Mockito.when(productRepo.findAllByCategory(cate, PageRequest.of(page - 1, size))).thenReturn(lists);

		Assert.assertEquals(2, service.findPagination(id, page, size).size());

	}
}
