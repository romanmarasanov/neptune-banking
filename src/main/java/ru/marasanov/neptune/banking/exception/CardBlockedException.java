package ru.marasanov.neptune.banking.exception;

import org.springframework.http.HttpStatus;

public class CardBlockedException extends AbstractApiException {
    public CardBlockedException(HttpStatus status, String message) {
        super(status, message);
    }
}
