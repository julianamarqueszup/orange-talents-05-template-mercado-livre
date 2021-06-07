package br.com.zupacademy.juliana.mercadolivre.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}