package com.bci.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ValidatorTest {

    @Autowired
    Validator validator;

    @Test
    void validatePassword() {
        String invalidPassword = "bci1234";
        String validPassword = "Bci1234.";
        Assertions.assertFalse(validator.isValidPassword(invalidPassword));
        Assertions.assertTrue(validator.isValidPassword(validPassword));
    }

}
