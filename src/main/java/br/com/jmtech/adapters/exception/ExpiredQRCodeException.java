package br.com.jmtech.adapters.exception;

public class ExpiredQRCodeException extends RuntimeException{

    public ExpiredQRCodeException(String message) {
        super(message);
    }
}
