package com.devfernandes.dscommerce.entities.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devfernandes.dscommerce.entities.Order;
import com.devfernandes.dscommerce.entities.OrderItem;
import com.devfernandes.dscommerce.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
