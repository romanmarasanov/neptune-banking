package ru.marasanov.neptune.banking.exception;

import org.springframework.http.HttpStatus;
import java.sql.Timestamp;

abstract public class AbstractApiException extends RuntimeException {
    private final HttpStatus status;
    private final Timestamp ts;

    public AbstractApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.ts = new Timestamp(System.currentTimeMillis());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Timestamp getTs() {
        return ts;
    }
}
