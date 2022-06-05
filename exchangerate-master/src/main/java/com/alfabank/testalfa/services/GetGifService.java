package com.alfabank.testalfa.services;

import com.alfabank.testalfa.domain.Gif;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.net.URI;

@FeignClient(name = "${gif.name}", url = "${gif.server.name}")
public interface GetGifService {
    @GetMapping()
    Gif getRandomGif(URI url);
}
