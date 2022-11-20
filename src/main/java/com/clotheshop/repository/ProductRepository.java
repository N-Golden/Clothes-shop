package com.clotheshop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.clotheshop.entities.Category;
import com.clotheshop.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByCategory(Category cate);

	List<Product> findAllByCategory(Category cate, Pageable pageble);
}
