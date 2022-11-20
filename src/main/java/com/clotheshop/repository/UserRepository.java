package com.clotheshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clotheshop.entities.User;

public interface UserRepository extends JpaRepository<User, String> {
	User findByEmail(String email);
}
