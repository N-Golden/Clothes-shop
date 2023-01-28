package com.clotheshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clotheshop.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query(value="select top 3 * from categorys", nativeQuery = true)
	public List<Category> find3Records();
}
