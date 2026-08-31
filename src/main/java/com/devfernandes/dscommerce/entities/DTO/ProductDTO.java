package com.devfernandes.dscommerce.entities.DTO;

import java.util.ArrayList;
import java.util.List;

import com.devfernandes.dscommerce.entities.Category;
import com.devfernandes.dscommerce.entities.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ProductDTO {
	
	
	private Long id;
	
	@Size(min = 3, max =80, message="nome precisa ser minimo 3 e maximo 80 caracter")
	@NotBlank(message="campo requerido")
	private String name;
	
	
	@Size( min =10, message="nome precisa ser minimo 10 caracteres")
	@NotBlank(message="campo requerido")
	private String description;
	
	@NotNull(message="Camppo requerido")
	@Positive(message="o valor deve ser positivo")
	private Double price;
	private String imgUrl;
	
	@NotEmpty(message ="deve ter pelo menos uma categoria")
	private List<CategoryDTO> categories = new ArrayList<>();
	
    public ProductDTO() {
    	
    }
	

	public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	

	public ProductDTO(Product entity) {
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		price = entity.getPrice();
		imgUrl = entity.getImagUrl();
		
		
		for(Category cat : entity.getCategories()) {
			categories.add(new CategoryDTO(cat));
		}
	}


	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getDescription() {
		return description;
	}


	public Double getPrice() {
		return price;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public List<CategoryDTO> getCategories() {
		return categories;
	}
	
	

	
}
