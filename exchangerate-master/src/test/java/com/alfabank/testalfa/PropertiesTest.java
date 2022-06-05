package com.alfabank.testalfa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PropertiesTest {
    @Value("${exchange.currency.server}")  String exchangeCurrencyServer;
    @Value("${exchange.api.id}")  String exchangeApiID;
    @Value("${currency.code}")  String currencyCode;
    @Value("${gif.server.name}")  String gifServerName;
    @Value("${gif.api.id}")  String gifApiID;
    @Value("${gif.rich}")  String rich;
    @Value("${gif.broke}") String broke;

    @Test
    void checkOptions() {
        Assertions.assertNotNull(exchangeCurrencyServer);
        Assertions.assertNotNull(exchangeApiID);
        Assertions.assertNotNull(currencyCode);
        Assertions.assertNotNull(gifServerName);
        Assertions.assertNotNull(gifApiID);
        Assertions.assertNotNull(rich);
        Assertions.assertNotNull(broke);
    }
}
