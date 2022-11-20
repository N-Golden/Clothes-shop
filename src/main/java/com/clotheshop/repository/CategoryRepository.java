package com.clotheshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clotheshop.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
