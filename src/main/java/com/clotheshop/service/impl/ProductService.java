package com.clotheshop.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clotheshop.dto.ProductDTO;
import com.clotheshop.entities.Category;
import com.clotheshop.entities.Product;
import com.clotheshop.repository.CategoryRepository;
import com.clotheshop.repository.ProductRepository;
import com.clotheshop.service.IConvertService;
import com.clotheshop.service.IProductService;

@Service
public class ProductService implements IProductService, IConvertService<ProductDTO, Product> {

	@Autowired
	ProductRepository results;

	@Autowired
	CategoryRepository results1;

	@Autowired
	ModelMapper mapper;

	@Override
	public List<Product> findAll() {
		return results.findAll();
	}

	@Override
	public List<Product> findPagination(Long id, Integer page, Integer size) {
		Category cate = results1.findById(id).get();
		Pageable pages = PageRequest.of(page - 1, size);
		List<Product> products = results.findAllByCategory(cate, pages);
		return products;
	}

	@Override
	public ProductDTO convertToDo(Product product) {
		ProductDTO model = mapper.map(product, ProductDTO.class);
		return model;
	}

	@Override
	public Product convertToEntity(ProductDTO model) {
		Product product = mapper.map(model, Product.class);
		return product;
	}

	@Override
	public Product findOne(Long id) {
		return results.findById(id).get();
	}

	@Override
	public void deleteOne(Long id) {
		results.deleteById(id);
	}

	@Override
	public void addAndUpdateOne(Product entity) {
		results.save(entity);
	}

	@Override
	public List<Product> findByCategory(Long idCate) {
		Category cate = results1.findById(idCate).get();
		return results.findByCategory(cate);
	}

}
