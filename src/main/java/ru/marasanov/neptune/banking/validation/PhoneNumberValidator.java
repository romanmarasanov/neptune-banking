package ru.marasanov.neptune.banking.validation;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class PhoneNumberValidator implements Predicate<String> {
    private final static String PHONE_REGEXP = "8[0-9]{10}";

    @Override
    public boolean test(String phoneNumber) {
        if (phoneNumber == null) return false;
        return phoneNumber.matches(PHONE_REGEXP);
    }
}
