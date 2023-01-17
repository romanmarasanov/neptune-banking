package ru.marasanov.neptune.banking.exception;

import org.springframework.http.HttpStatus;

public class CardNotFoundException extends AbstractApiException {
    public CardNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
