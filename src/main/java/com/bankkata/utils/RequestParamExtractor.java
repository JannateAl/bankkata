package com.bankkata.utils;

import java.math.BigDecimal;

import com.sun.net.httpserver.HttpExchange;

public class RequestParamExtractor {

    public static BigDecimal extractAmount(HttpExchange exchange) {
        String query = exchange.getRequestURI().getQuery();
        if (query == null || !query.contains("amount=")) {
            throw new IllegalArgumentException("Missing 'amount' parameter");
        }

        try {
            String amountStr = query.split("amount=")[1];
            return new BigDecimal(amountStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid 'amount' format");
        }
    }
    
}
