package ru.marasanov.neptune.banking.exception;

import org.springframework.http.HttpStatus;

public class NotEnoughAmountException extends AbstractApiException {
    public NotEnoughAmountException(HttpStatus status, String message) {
        super(status, message);
    }
}
