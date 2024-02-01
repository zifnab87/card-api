package com.cardapi.cardapi.adapters.web.bintable.stubs;

import com.cardapi.cardapi.adapters.web.bintable.BinLookUpResponse;
import com.cardapi.cardapi.adapters.web.bintable.ports.BinLookUpPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("test")
public class BinLookUpAdapterStub implements BinLookUpPort {

    @Override
    public BinLookUpResponse binLookup(String bin) {
        String isoCode = "EG";
        if (bin.startsWith("123456")) {
            isoCode = "US";
        }
        return new BinLookUpResponse(200, "message", new BinLookUpResponse.Data(new BinLookUpResponse.Country(isoCode)));

    }
}
