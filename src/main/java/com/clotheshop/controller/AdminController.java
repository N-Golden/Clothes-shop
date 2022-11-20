package com.clotheshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clotheshop.entities.Product;
import com.clotheshop.service.IProductService;

@Controller
public class AdminController {

	@Autowired
	private IProductService service;

	@GetMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("products", service.findAll());
		return "admin/admin";
	}

	@GetMapping("/admin/post")
	public String add() {
		return "admin/post";
	}

	@PostMapping("/admin/post")
	public String edit(Model model, @RequestParam(required = false) long id) {
		Product entity = service.findOne(id);
		model.addAttribute("model", entity);
		return "admin/post";
	}
}
