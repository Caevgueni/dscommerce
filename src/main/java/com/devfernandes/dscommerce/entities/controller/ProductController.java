package com.devfernandes.dscommerce.entities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devfernandes.dscommerce.entities.DTO.ProductDTO;
import com.devfernandes.dscommerce.entities.services.ProductService;

@RestController
@RequestMapping(value="/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping(value="/{id}")
	public ProductDTO findById(@PathVariable Long id) {
		
		
		return productService.findById(id);
		
	
		
		/*
		 * 
		 * # formadetalhada
		 * ProductDTO  dto = productService.findById(id);
		
		return dto;
		 */
	}

}
