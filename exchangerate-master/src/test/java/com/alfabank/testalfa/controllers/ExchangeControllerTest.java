package com.alfabank.testalfa.controllers;

import com.alfabank.testalfa.domain.Data;
import com.alfabank.testalfa.domain.Exchange;
import com.alfabank.testalfa.domain.Gif;
import com.alfabank.testalfa.services.ExchangeService;

import com.alfabank.testalfa.services.GetGifService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.alfabank.testalfa.utils.Util.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class ExchangeControllerTest {
    @Value("${exchange.currency.server}")  String exchangeCurrencyServer;
    @Value("${exchange.api.id}")  String exchangeApiID;
    @Value("${currency.code}")  String currencyCode;
    @Value("${gif.server.name}")  String gifServerName;
    @Value("${gif.api.id}")  String gifApiID;
    @Value("${gif.rich}")  String rich;
    @Value("${gif.broke}") String broke;

    Map<String, Double> doubleMap = new HashMap<>();
    {
        doubleMap.put("AED", 3.6731);
        doubleMap.put("RUB", 63.360006);
    }
    Exchange exchange = new Exchange("disclaimer", "license", "USD", doubleMap);
    Exchange exchange1 = new Exchange("disclaimer", "license", "USD", doubleMap);

    Data testData = new Data(
            "wPcyXfZ547NqNSzSG4rO9t9bgpn0b90n",
            "Suck On It Pauley Perrette GIF by CBS",
            "g",
            "https://giphy.com/embed/Y42sxO22o6oUCHvhXF",
            "https://giphy.com/embed/Y42sxO22o6oUCHvhXF"
    );
    Gif testGif = new Gif(testData);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ExchangeService exchangeService;
    @MockBean
    private GetGifService getGifService;

    protected ResultActions perform(MockHttpServletRequestBuilder builder) throws Exception {
        return mockMvc.perform(builder);
    }

    @BeforeEach
    void beforeEach() {
        URI exToday = getExchangeURI(exchangeCurrencyServer, exchangeApiID, currencyCode, LocalDateTime.now());
        URI exYest = getExchangeURI(exchangeCurrencyServer,exchangeApiID,currencyCode, LocalDateTime.now().minusDays(1));
        URI gifURI = getGifURI(gifServerName, gifApiID, broke);

        when(exchangeService.getExchangeRates(exToday)).thenReturn(exchange);
        when(exchangeService.getExchangeRates(exYest)).thenReturn(exchange1);
        when(getGifService.getRandomGif(gifURI)).thenReturn(testGif);
    }


    @Test
    void getGif() throws Exception {
        perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andDo(print());

    }

    @Test
    void getGifAsJSON() throws Exception {
        perform(MockMvcRequestBuilders.get("/gbp"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}