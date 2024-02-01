package com.cardapi.cardapi.adapters.web.bintable.ports;

import com.cardapi.cardapi.adapters.web.bintable.BinLookUpResponse;

public interface BinLookUp {
    BinLookUpResponse binLookup(String bin);
}
