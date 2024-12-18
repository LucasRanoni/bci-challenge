package com.bci.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator {
    @Value("${email.validation.regex}")
    private String emailValidationRegex;

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(emailValidationRegex, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).find();
    }
}