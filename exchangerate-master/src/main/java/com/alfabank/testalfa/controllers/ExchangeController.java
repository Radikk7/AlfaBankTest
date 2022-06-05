package com.alfabank.testalfa.controllers;

import com.alfabank.testalfa.domain.Exchange;
import com.alfabank.testalfa.domain.Gif;
import com.alfabank.testalfa.services.ExchangeService;
import com.alfabank.testalfa.services.GetGifService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;

import static com.alfabank.testalfa.utils.Util.*;

@RestController
public class ExchangeController {

    @Value("${exchange.currency.server}")  String exchangeCurrencyServer;
    @Value("${exchange.api.id}")  String exchangeApiID;
    @Value("${currency.code}")  String currencyCode;
    @Value("${gif.server.name}")  String gifServerName;
    @Value("${gif.api.id}")  String gifApiID;
    @Value("${gif.rich}")  String rich;
    @Value("${gif.broke}") String broke;

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private GetGifService getGifService;




    @GetMapping("/{code}")
    public Gif getGif(@PathVariable String code) {

        URI link = getCurrentURL(exchangeCurrencyServer,currencyCode,exchangeApiID);
        Exchange currentsRates = exchangeService.getExchangeRates(link);


        URI linkDate = getExchangeURI(exchangeCurrencyServer,currencyCode,exchangeApiID,LocalDateTime.now());
        Exchange yersteday = exchangeService.getExchangeRates(linkDate);


        boolean isPrewuisRateMoore= currentsRates.getRates().get(currencyCode.toUpperCase()) > yersteday.getRates().
                get(currencyCode.toUpperCase());


            Gif gif = new Gif();
        if (isPrewuisRateMoore){
            gif = getGifService.getRandomGif(getGifURI(gifServerName, gifApiID, rich));

        }
        else {
           gif = getGifService.getRandomGif(getGifURI(gifServerName, gifApiID, broke));
        }
        return gif;
    }

}
