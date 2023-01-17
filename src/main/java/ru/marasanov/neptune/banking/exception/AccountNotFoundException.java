package ru.marasanov.neptune.banking.exception;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends AbstractApiException {
    public AccountNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
