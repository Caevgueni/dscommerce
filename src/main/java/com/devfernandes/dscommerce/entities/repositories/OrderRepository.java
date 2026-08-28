package com.devfernandes.dscommerce.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devfernandes.dscommerce.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
