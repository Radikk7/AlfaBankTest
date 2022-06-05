package com.alfabank.testalfa.services;

import com.alfabank.testalfa.domain.Gif;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static com.alfabank.testalfa.utils.Util.getGifURI;

@SpringBootTest
class GifServiceTest {
    @Value("${gif.server.name}")  String gifServerName;
    @Value("${gif.api.id}")  String gifApiID;
    @Value("${gif.rich}")  String rich;

    @Autowired
    GetGifService getGifService;

    @Test
    void getGif() {
        Gif gif = getGifService.getRandomGif(getGifURI(gifServerName, gifApiID, rich));
        Assertions.assertNotNull(gif);
    }
}