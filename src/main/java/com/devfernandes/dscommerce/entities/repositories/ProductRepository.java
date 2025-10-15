package com.devfernandes.dscommerce.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devfernandes.dscommerce.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
