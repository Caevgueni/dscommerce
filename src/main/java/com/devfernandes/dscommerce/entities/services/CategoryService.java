package com.devfernandes.dscommerce.entities.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfernandes.dscommerce.entities.Category;
import com.devfernandes.dscommerce.entities.DTO.CategoryDTO;
import com.devfernandes.dscommerce.entities.repositories.CategoryRepository;


@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository  categoryRepository;
	
	
	@Transactional(readOnly=true)
     public List<CategoryDTO> findAll(){
    	 
    	 List<Category> result = categoryRepository.findAll();
    	 return result.stream().map(x -> new CategoryDTO(x)).toList();
     }
	
	

}
