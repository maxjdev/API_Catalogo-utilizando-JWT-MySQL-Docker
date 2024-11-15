package br.com.catalog.controller.responses;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record Response<T>(
        @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
        LocalDateTime timestamp,
        String status,
        String message,
        T data
) {
    public Response(String status, String message, T data) {
        this(LocalDateTime.now(), status, message, data);
    }
}
