package com.devfernandes.dscommerce.entities.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfernandes.dscommerce.entities.Product;
import com.devfernandes.dscommerce.entities.DTO.ProductDTO;
import com.devfernandes.dscommerce.entities.repositories.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository  productRepository;
	

	@Transactional(readOnly=true)
	public ProductDTO findById(Long id){
		
		 Optional<Product> result = productRepository.findById(id);
		 
		 Product  product= productRepository.findById(id).get();
		 
		 return new ProductDTO(product);
		 
		 
		 /*
		  * #opçao detalhada
		  * 
		  Optional<Product> result = productRepository.findById(id);
		 
		 Product  product= result.get();
		 
		 ProductDTO  dto= new ProductDTO(product);
		 return dto;
		  
		  */
	}	

}
