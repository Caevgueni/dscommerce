package com.devfernandes.dscommerce.entities.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devfernandes.dscommerce.entities.DTO.ProductDTO;
import com.devfernandes.dscommerce.entities.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {

		Page<ProductDTO> dto = productService.findAll(pageable);

		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {

		ProductDTO dto = productService.findById(id);

		return ResponseEntity.ok(dto);

		/*
		 * 
		 * # formadetalhada ProductDTO dto = productService.findById(id);
		 * 
		 * return dto;
		 */
	}

	@PostMapping
	public ResponseEntity<ProductDTO> insert(@Valid @RequestBody ProductDTO dto) {

		dto = productService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> update( @PathVariable Long id, @Valid @RequestBody ProductDTO dto) {

		dto = productService.update(id, dto);
		return ResponseEntity.ok(dto);
		
		

}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id) {

		productService.delet(id);
		return ResponseEntity.noContent().build();
		
		

}
}
