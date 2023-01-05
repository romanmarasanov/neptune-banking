package ru.marasanov.neptune.banking.validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class EmailValidator implements Predicate<String> {
    private final static String EMAIL_REGEXP = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9-]+.[a-zA-Z]{2,7}$";

    @Override
    public boolean test(String email) {
        if (email == null) return false;
        return email.matches(EMAIL_REGEXP);
    }
}
