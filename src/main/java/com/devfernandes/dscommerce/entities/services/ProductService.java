package com.devfernandes.dscommerce.entities.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.devfernandes.dscommerce.entities.Product;
import com.devfernandes.dscommerce.entities.DTO.ProductDTO;
import com.devfernandes.dscommerce.entities.repositories.ProductRepository;
import com.devfernandes.dscommerce.entities.services.exceptions.DatabaseException;
import com.devfernandes.dscommerce.entities.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


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
		 
		 Product  product= productRepository.findById(id).orElseThrow(
				 () -> new ResourceNotFoundException("Recurso não encontrado") );
		 
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
		
		try {
		Product entity = productRepository.getReferenceById(id);
		copyDtoToEntity(dto, entity);
		entity= productRepository.save(entity);
		return  new ProductDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Recurso nao encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void delet(Long id) {
		if(!productRepository.existsById(id)) {
			
			throw new ResourceNotFoundException("Recurso o encontrado");
		}
		try {
		productRepository.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("falha de integridade referencial");
		}
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImagUrl(dto.getImgUrl());
	}
	

}
