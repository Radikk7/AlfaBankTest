package com.alfabank.testalfa.services;

import com.alfabank.testalfa.domain.Exchange;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "${exchange.name}",url = "${exchange.currency.server}")
public interface ExchangeService {

    @GetMapping()
    Exchange getExchangeRates(URI baseUrl);
}
