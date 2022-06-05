package com.alfabank.testalfa.services;

import com.alfabank.testalfa.domain.Exchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.alfabank.testalfa.utils.Util.getExchangeURI;

@SpringBootTest
class ExchangeServiceTest {

    @Value("${exchange.server}") private String exServer;
    @Value("${exchange.app_id}") private String exchangeAppID;
    @Value("${exchange.base}") private String curBase;

    @Autowired
    public ExchangeService exchangeService;

    @Test
    void getExchange() {
        Exchange exchange = exchangeService.getExchangeRates(getExchangeURI(exServer, exchangeAppID, curBase, LocalDateTime.now()));
        Assertions.assertNotNull(exchange);
    }
}