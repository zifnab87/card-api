package com.cardapi.cardapi.adapters.web.bintable;

import com.cardapi.cardapi.adapters.web.bintable.ports.BinLookUpPort;
import com.cardapi.cardapi.helpers.ValueWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Profile("dev | prod")
public class BinLookUpAdapter implements BinLookUpPort {

    private final ValueWrapper valueWrapper;

    @Override
    public BinLookUpResponse binLookup(String bin) {
        String url = "https://api.bintable.com/v1/"+ bin +"?api_key=" + valueWrapper.apiKey;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, BinLookUpResponse.class);

    }
}
