package ru.marasanov.neptune.banking.exception;

import org.springframework.http.HttpStatus;

public class NotValidJSONValueException extends AbstractApiException {
    public NotValidJSONValueException(HttpStatus status, String message) {
        super(status, message);
    }
}
