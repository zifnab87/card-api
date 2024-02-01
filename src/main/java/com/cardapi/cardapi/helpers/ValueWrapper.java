package com.cardapi.cardapi.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ValueWrapper {

    @Value("${apiKey}")
    public String apiKey;

    @Value("${spring.profiles.active:}")
    public String activeProfile;
}
