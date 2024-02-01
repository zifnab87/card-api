package com.cardapi.cardapi;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "test")
@SpringBootTest(properties = "spring.profiles.active:test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class AbstractIntegrationTest {
}
