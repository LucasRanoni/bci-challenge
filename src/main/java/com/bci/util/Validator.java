package com.bci.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validator {

    private final String emailValidationRegex;

    private final String passwordValidationRegex;

    public Validator(@Value("${email.validation.regex}") String email, @Value("${password.validation.regex}") String password) {
        emailValidationRegex = email;
        passwordValidationRegex = password;
    }

    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(emailValidationRegex);
        return match(pattern, email);
    }

    private boolean match(Pattern pattern, String wordToMatch) {
        return pattern.matcher(wordToMatch).find();
    }

    public boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(passwordValidationRegex);
        return match(pattern, password);
    }


}