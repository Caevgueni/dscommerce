package com.devfernandes.dscommerce.entities.DTO;

import java.time.Instant;

import com.devfernandes.dscommerce.entities.Payment;

public class PaymentDTO {
	
	private Long id;
	private Instant moment;
	
	
	public PaymentDTO(Long id, Instant moment) {
		super();
		this.id = id;
		this.moment = moment;
	}
	
	public PaymentDTO(Payment entity) {
		
		id = entity.getId();
		moment = entity.getMoment();
	}

	public Long getId() {
		return id;
	}

	public Instant getMoment() {
		return moment;
	}
	
	
	

}
