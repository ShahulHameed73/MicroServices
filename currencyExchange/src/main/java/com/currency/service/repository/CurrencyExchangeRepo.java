package com.currency.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.currency.service.entity.CurrencyExchange;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long> {

	Optional<CurrencyExchange> findByFromCurrencyAndToCurrency(String from, String to);

}
