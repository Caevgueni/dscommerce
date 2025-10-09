package com.devfernandes.dscommerce.entities;

import java.time.Instant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant moment;
	
	private OrderStatus status;
	
	@ManyToOne
	@JoinColumn(name="tb_cliente")
	private User client;
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)	
	private Payment payment;
	
	public Order(Long id, Instant moment, OrderStatus status, Payment payment, User client) {
		this.id = id;
		this.moment = moment;
		this.status = status;
		this.payment= payment;
		this.client= client;
		
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public Instant getMoment() {
		return moment;
	}



	public void setMoment(Instant moment) {
		this.moment = moment;
	}



	public OrderStatus getStatus() {
		return status;
	}



	public Payment getPayment() {
		return payment;
	}



	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	
}
