package com.cardapi.cardapi;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "test")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public abstract class AbstractUnitTest {
}
