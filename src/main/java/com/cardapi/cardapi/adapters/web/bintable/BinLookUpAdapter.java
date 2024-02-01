package com.cardapi.cardapi.adapters.web.bintable;

import com.cardapi.cardapi.adapters.web.bintable.ports.BinLookUp;
import com.cardapi.cardapi.helpers.ValueWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class BinLookUpAdapter implements BinLookUp {

    private final ValueWrapper valueWrapper;

    @Override
    public BinLookUpResponse binLookup(String bin) {
        String url = "https://api.bintable.com/v1/"+ bin +"?api_key=" + valueWrapper.apiKey;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, BinLookUpResponse.class);

    }
}
