package com.clotheshop.service;

import java.util.List;

import com.clotheshop.entities.Product;

public interface IProductService {
	List<Product> findAll();

	List<Product> findPagination(Long id, Integer page, Integer size);

	Product findOne(Long id);

	void deleteOne(Long id);

	void addAndUpdateOne(Product entity);

	List<Product> findByCategory(Long idCate);

}
