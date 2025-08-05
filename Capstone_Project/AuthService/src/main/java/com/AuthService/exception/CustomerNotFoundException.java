package com.AuthService.exception;

public class CustomerNotFoundException extends Exception {
    private String message;

    public CustomerNotFoundException(String message) {
        this.message = message;
    }

    public CustomerNotFoundException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    @Override
    public String toString() {
        return "CustomerNotFoundException{" +
                "message='" + message + '\'' +
                '}';
    }
}
