package br.com.catalog.exceptions.abstract_handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

public abstract class AbstractExceptionHandler {
    private final Logger log;

    protected AbstractExceptionHandler(Logger log) {
        this.log = log;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handlerGeneric(final Exception ex) {
        log.error("UNEXPECTED ERROR", ex);
        var apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected internal server error occured");
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public record ApiError(
            @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
            LocalDateTime timestamp,
            int code,
            String status,
            String description
    ) {
        public ApiError(int code, String status, String description) {
            this(LocalDateTime.now(), code, status, description);
        }
    }
}
