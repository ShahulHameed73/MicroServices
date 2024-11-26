package com.currency.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.currency.service.entity.CurrencyExchange;

@FeignClient(name = "currencyExchange") 
	public interface FeignClientCode {
		 @GetMapping("/from/{from}/to/{to}")
		 CurrencyExchange retrieveExchangeValue(@PathVariable String from, 
				  @PathVariable String to);
	}


