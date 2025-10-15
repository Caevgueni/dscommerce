package com.devfernandes.dscommerce.entities.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
     public Page<ProductDTO> findAll(Pageable pageable){
    	 
    	 Page<Product> result = productRepository.findAll(pageable);
    	 return result.map(x -> new ProductDTO(x));
     }
	
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
	
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = productRepository.save(entity);
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		Product entity = productRepository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity= productRepository.save(entity);
		return  new ProductDTO(entity);
	}

	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImagUrl(dto.getImgUrl());
	}
	

}
