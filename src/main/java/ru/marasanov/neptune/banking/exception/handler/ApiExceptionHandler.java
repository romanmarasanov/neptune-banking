package ru.marasanov.neptune.banking.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.marasanov.neptune.banking.exception.AbstractApiException;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {AbstractApiException.class})
    public ResponseEntity<ApiExceptionResponse> handle(AbstractApiException e) {
        ApiExceptionResponse response = new ApiExceptionResponse(
                e.getMessage(),
                e.getStatus(),
                e.getTs()
        );

        return new ResponseEntity<>(response, e.getStatus());
    }
}
