package com.cardapi.cardapi.adapters.web.bintable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BinLookUpResponse {
    private int result;
    private String message;
    private Data data;


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private Country country;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Country {
        private String code;
    }
}