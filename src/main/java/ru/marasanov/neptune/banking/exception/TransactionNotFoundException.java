package ru.marasanov.neptune.banking.exception;

import org.springframework.http.HttpStatus;

public class TransactionNotFoundException extends AbstractApiException {
    public TransactionNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
