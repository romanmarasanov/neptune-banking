package ru.marasanov.neptune.banking.exception;

import org.springframework.http.HttpStatus;

public class NotValidFormDataException extends AbstractApiException {
    public NotValidFormDataException(HttpStatus status, String message) {
        super(status, message);
    }
}
