package br.com.jmtech.interfaceAdapters.exception;

public class ExpiredQRCodeException extends RuntimeException{

    public ExpiredQRCodeException(String message) {
        super(message);
    }
}
