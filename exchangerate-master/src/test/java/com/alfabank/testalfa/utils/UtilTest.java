package com.alfabank.testalfa.utils;

import com.alfabank.testalfa.domain.Exchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@SpringBootTest
class UtilTest {
    @Value("${exchange.currency.server}") String exchangeCurrencyServer;
    @Value("${exchange.api.id}")  String exchangeApiID;
    @Value("${currency.code}")  String currencyCode;
    @Value("${gif.server.name}")  String gifServerName;
    @Value("${gif.api.id}")  String gifApiID;
    @Value("@{exchange.name}") String testname;
    @Value("${gif.rich}")  String rich;
    @Test
    void getExchangeURI()  {
        URI uri_test = Util.getExchangeURI(exchangeCurrencyServer, exchangeApiID, currencyCode, LocalDateTime.now());
        StringBuilder stringBuilder= new StringBuilder();
      stringBuilder.append(exchangeCurrencyServer).append("latest.json?app_id=").append(exchangeApiID).
              append("&base=").append(currencyCode.toUpperCase());
     URI uri = URI.create("https://openexchangerates.org/api/latest.json?app_id="+"c727627f3b054d96b7d87557938701ae"+"&base=USD");
       //URI uri = URI.create(stringBuilder.toString());
      //  System.out.println(uri);
        Assertions.assertTrue(uri.equals(uri_test));

    }

    @Test
    void getGifURI() {
        URI uri_test = Util.getGifURI(gifServerName, gifApiID, "rich");
        StringBuilder stringBuilder = new StringBuilder();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd");
        String historicalDate = f.format(LocalDateTime.now());
        stringBuilder.append(gifServerName).append("v1/gifs/random?api_key=").append(gifApiID).append("&tag=").append(rich);
        URI uri = URI.create(stringBuilder.toString());
        Assertions.assertTrue(uri.equals(uri_test));
    }


}