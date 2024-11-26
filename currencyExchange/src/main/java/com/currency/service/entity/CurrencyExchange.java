package com.currency.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	
@NoArgsConstructor 
@AllArgsConstructor
@Entity

public class CurrencyExchange {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 	private Long id;
	private String fromCurrency; 	
    private String toCurrency;
    private Double conversionMultiple;
	
	
}
