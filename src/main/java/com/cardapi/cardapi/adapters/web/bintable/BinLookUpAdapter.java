package com.cardapi.cardapi.adapters.web.bintable;

import com.cardapi.cardapi.adapters.web.bintable.ports.BinLookUp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BinLookUpAdapter implements BinLookUp {

    @Value("${apiKey}")
    private String apiKey;

    @Override
    public BinLookUpResponse binLookup(String bin) {
        String url = "https://api.bintable.com/v1/"+ bin +"?api_key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, BinLookUpResponse.class);

    }
}
