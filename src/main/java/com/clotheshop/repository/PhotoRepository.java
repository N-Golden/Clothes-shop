package com.clotheshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clotheshop.entities.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Long>{
	@Query(value="select * from photos where product_id = ?1", nativeQuery = true)
	List<Photo> findByProductId(Long id);
}
