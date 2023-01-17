package ru.marasanov.neptune.banking.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class ApiExceptionResponse {
    private final String message;
    private final HttpStatus status;
    private final Timestamp ts;
}
