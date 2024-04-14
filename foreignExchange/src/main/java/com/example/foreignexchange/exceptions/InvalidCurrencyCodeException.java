package com.example.foreignexchange.exceptions;

    public class InvalidCurrencyCodeException extends RuntimeException {
        public InvalidCurrencyCodeException(String message) {
            super(message);
        }
    }

