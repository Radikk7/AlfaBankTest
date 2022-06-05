package com.alfabank.testalfa.utils;

import org.springframework.util.Assert;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Util {

    public static URI getCurrentURL(String serverName, String currenseCode,String apiKey){
        if (serverName == null || currenseCode==null || apiKey ==null){
            return null;
        }
        StringBuilder buildExchangeMoneyUrl = new StringBuilder();
        buildExchangeMoneyUrl.append(serverName).append("latest.json?app_id=").append(apiKey).
                append("&base=").append(currenseCode.toUpperCase());
        return URI.create(buildExchangeMoneyUrl.toString()) ;

    }

    public static URI getExchangeURI(String serverName,String currenseCode, String apiKey,  LocalDateTime date) {
        if (serverName ==null || apiKey == null || currenseCode == null ){
            return null;
        }
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "yyyy-MM-dd");
        String historicalDate = f.format(date);
        StringBuilder buildExchangeMoneyUrl = new StringBuilder();
        return URI.create(new StringBuilder().append(serverName).append("historical/").
                append(historicalDate).append(".json?app_id=").append(apiKey).append("&base=").
                append(currenseCode.toUpperCase()).toString());



    }

    public static URI getGifURI(String gifServer, String gifApiID, String tag) {
        Assert.notNull(gifServer,"field gifServer is null");
        Assert.notNull(gifApiID,"field gifApiID is null");
        Assert.notNull(tag,"field tag is null");
        StringBuilder builderURL = new StringBuilder();
        builderURL.append(gifServer).append("v1/gifs/random?api_key=").append(gifApiID).append("&tag=").append(tag);
         return URI.create(builderURL.toString());
    }

 // public static boolean compareExchangeRate(Exchange exch1, Exchange exch2, String currency) {
 //     return exch1.getRates().get(currency.toUpperCase()) > exch2.getRates().get(currency.toUpperCase());
 // }

}
