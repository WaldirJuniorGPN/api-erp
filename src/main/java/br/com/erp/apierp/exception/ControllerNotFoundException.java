package br.com.erp.apierp.exception;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String msg) {
        super(msg);
    }
}
