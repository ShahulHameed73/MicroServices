package com.currency.service.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currency.service.entity.CurrencyExchange;
import com.currency.service.repository.CurrencyExchangeRepo;

@RestController
public class CurrencyExchangeController {
	@Autowired
	CurrencyExchangeRepo currencyExchangeRepo;
		
       @GetMapping("/from/{from}/to/{to}")
	    public ResponseEntity<CurrencyExchange> retrieveExchangeValue(@PathVariable String from, 
	                           @PathVariable String to) {
	     // Assuming you have a service to fetch the exchange rate
	      Optional<CurrencyExchange> optionalCurrencyExchange = currencyExchangeRepo.findByFromCurrencyAndToCurrency(from, to);
	       
	        if(optionalCurrencyExchange.isPresent())
	        {	
	        	CurrencyExchange currencyExchange = optionalCurrencyExchange.get();
	        	
	        	 return ResponseEntity.status(HttpStatus.OK)
    					 .header("status", "data retrived successfully...")
    					 .body(currencyExchange);
	        }
	        else
	        {  	 return ResponseEntity.status(HttpStatus.NOT_FOUND)
    					 .header("status", "data not found .")
    					 .body(null);
	        }
	
       }
       }