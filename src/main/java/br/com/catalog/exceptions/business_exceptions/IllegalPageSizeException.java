package br.com.catalog.exceptions.business_exceptions;

public class IllegalPageSizeException extends RuntimeException {
    public IllegalPageSizeException(String message) {
        super(message);
    }
}
