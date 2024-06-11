package br.com.erp.apierp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private StandardError error = new StandardError();

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ControllerNotFoundException exception, HttpServletRequest request) {
        return this.atribuirErro(HttpStatus.NOT_FOUND, "Entity not found", exception, request);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<StandardError> propertyReferenceException(PropertyReferenceException exception, HttpServletRequest request) {
        return this.atribuirErro(HttpStatus.BAD_REQUEST, "Property reference invalid", exception, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException exception, HttpServletRequest request) {
        var validateError = new ValidateError();
        var status = HttpStatus.BAD_REQUEST;
        validateError.setTimestamp(Instant.now());
        validateError.setStatus(status.value());
        validateError.setError("Erro de validação");
        validateError.setMessage(exception.getMessage());
        validateError.setPath(request.getRequestURI());

        for (FieldError f : exception.getBindingResult().getFieldErrors()) {
            validateError.addMensagens(f.getField(), f.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(validateError);
    }

    private ResponseEntity<StandardError> atribuirErro(HttpStatus status, String mensagemError, RuntimeException exception, HttpServletRequest request) {
        this.error.setTimestamp(Instant.now());
        this.error.setStatus(status.value());
        this.error.setError(mensagemError);
        this.error.setMessage(exception.getMessage());
        this.error.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(this.error);
    }
}
