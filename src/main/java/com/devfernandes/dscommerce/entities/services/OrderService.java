package com.devfernandes.dscommerce.entities.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfernandes.dscommerce.entities.Order;
import com.devfernandes.dscommerce.entities.DTO.OrderDTO;
import com.devfernandes.dscommerce.entities.repositories.OrderRepository;
import com.devfernandes.dscommerce.entities.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Optional<Order> result = orderRepository.findById(id);

		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

		return new OrderDTO(order);

	}

	

}
