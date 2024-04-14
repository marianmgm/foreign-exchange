package com.example.foreignexchange.utils;

import com.example.foreignexchange.exceptions.InvalidCurrencyCodeException;

import java.util.List;

public class CurrencyCodeValidator {
    private static final List<String> VALID_CODES = CurrencyCodes.VALID_CODES;

    public static void validateCurrencyCodes(String sourceCurrency, String targetCurrency) {
        if (!isValidCurrencyCode(sourceCurrency) || !isValidCurrencyCode(targetCurrency)) {
            throw new InvalidCurrencyCodeException("Unknown currency code(s) provided");
        }
    }

    private static boolean isValidCurrencyCode(String currencyCode) {
        return currencyCode != null && currencyCode.length() == 3 && VALID_CODES.contains(currencyCode.toUpperCase());
    }
}