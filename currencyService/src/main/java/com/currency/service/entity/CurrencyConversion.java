package com.currency.service.entity;

import java.time.LocalDateTime;

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

public class CurrencyConversion {
   
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String fromCurrency; // Source currency
	
    private String toCurrency; // Target currency
    
    
    private double conversionMultiple; // Conversion rate
    private int quantity; // Quantity to convert
    private double totalCalculatedAmount; // Total calculated amount after conversion
    private LocalDateTime localDateTime ;
	
}
