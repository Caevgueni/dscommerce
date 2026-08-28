package com.devfernandes.dscommerce.entities.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devfernandes.dscommerce.entities.Order;
import com.devfernandes.dscommerce.entities.OrderItem;
import com.devfernandes.dscommerce.entities.OrderStatus;
import com.devfernandes.dscommerce.entities.Product;
import com.devfernandes.dscommerce.entities.User;
import com.devfernandes.dscommerce.entities.DTO.OrderDTO;
import com.devfernandes.dscommerce.entities.DTO.OrderItemDTO;
import com.devfernandes.dscommerce.entities.repositories.OrderItemRepository;
import com.devfernandes.dscommerce.entities.repositories.OrderRepository;
import com.devfernandes.dscommerce.entities.repositories.ProductRepository;
import com.devfernandes.dscommerce.entities.services.exceptions.ResourceNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
    private UserService userService;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private AuthService  authService;
	
	@Transactional(readOnly = true)
	public OrderDTO findById(Long id) {
		Optional<Order> result = orderRepository.findById(id);

		Order order = orderRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
              authService.validateSelfOrAdmin(order.getClient().getId());
		return new OrderDTO(order);

	}
	
	
	@Transactional
	public OrderDTO insert(OrderDTO dto) {
		
		Order order = new Order();
		
		order.setMoment(Instant.now());
		order.setStatus(OrderStatus.WAITING_PAYMENT);
		
		User user = userService.autheticated();
		order.setClient(user);
		
		for(OrderItemDTO itemDto : dto.getItems()) {
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			OrderItem item = new OrderItem(product,order ,itemDto.getQuantity(), product.getPrice());
		     order.getItems().add(item);
		}
		
		orderRepository.save(order);
		
		orderItemRepository.saveAll(order.getItems());
		return new OrderDTO(order);
	}

	

}
