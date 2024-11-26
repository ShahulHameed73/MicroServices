package com.currency.service.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.currency.service.entity.CurrencyConversion;
import com.currency.service.entity.CurrencyExchange;
import com.currency.service.feign.FeignClientCode;
import com.currency.service.repository.CurrencyRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@RestController


public class CurrencyController {
	@Autowired
	CurrencyRepository currencyRepo;
	
	@Autowired
	FeignClientCode feignClientCode;
	

	@GetMapping("/from/{from}/to/{to}/quantity/{quantity}")
	@CircuitBreaker(name = "currencyService", fallbackMethod = "currencyExchangeFallback")
    public ResponseEntity<CurrencyConversion> calculateCurrencyConversion(
            @PathVariable String from, @PathVariable String to,
            @PathVariable int quantity) {

		 CurrencyExchange currencyExchange = feignClientCode.retrieveExchangeValue(from, to);
		 Double conversionMultiple = currencyExchange.getConversionMultiple();
		
		  double totalCalculatedAmount = quantity * conversionMultiple;
		    CurrencyConversion currencyConversion = new CurrencyConversion();
	        currencyConversion.setFromCurrency(from);
	        currencyConversion.setToCurrency(to);
	        currencyConversion.setQuantity(quantity);
	        currencyConversion.setConversionMultiple(conversionMultiple);
	        currencyConversion.setTotalCalculatedAmount(totalCalculatedAmount);
	        currencyConversion.setLocalDateTime(LocalDateTime.now());
	        
	        currencyRepo.save(currencyConversion);
	        return ResponseEntity.status(HttpStatus.OK)
                    .header("value", "amount calculated....")
                    .body(currencyConversion);
	}
	public ResponseEntity<CurrencyConversion> currencyExchangeFallback(
	        String from, String to, int quantity, Throwable ex) {

	    // Log the exception if needed
	    System.out.println("Fallback triggered for from = " + from + ", to = " + to + " due to " + ex.getMessage());

	    // Provide a default response or a custom fallback response
	    CurrencyExchange fallbackResponse = new CurrencyExchange();
	    fallbackResponse.setFromCurrency(from);
	    fallbackResponse.setToCurrency(to);
	    fallbackResponse.setConversionMultiple(0.0);  // Default value if the service is down
	    
	    CurrencyConversion currencyConversion = new CurrencyConversion();
	    currencyConversion.setFromCurrency(from);
	    currencyConversion.setToCurrency(to);
	    currencyConversion.setQuantity(quantity);
	    currencyConversion.setConversionMultiple(fallbackResponse.getConversionMultiple());
	    currencyConversion.setTotalCalculatedAmount(quantity * fallbackResponse.getConversionMultiple());
	    currencyConversion.setLocalDateTime(LocalDateTime.now());
	    
	    // You can optionally log or save the failed conversion if needed
	    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            .header("value", "Fallback: currency conversion failed due to service unavailability")
	            .body(currencyConversion);
	}
	
}
