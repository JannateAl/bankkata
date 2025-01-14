package com.bankkata.controller;

import com.bankkata.service.BankAccountService;
import com.bankkata.service.BankAccountServiceImpl;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(String client, BigDecimal initialAmount) {
        // Crée un compte bancaire initial avec 1000 EUR pour un client 'John Doe'.
        this.bankAccountService = new BankAccountServiceImpl(client, initialAmount);
    }

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        
        // deposit endpoint
        server.createContext("/deposit", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = handleTransaction(exchange, "deposit");
                sendResponse(exchange, response);
            }
        });

        // withdraw endpoint
        server.createContext("/withdraw", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = handleTransaction(exchange, "withdraw");
                sendResponse(exchange, response);
            }
        });

        // printing statement endpoint
        server.createContext("/statement", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                sendResponse(exchange, bankAccountService.printStatement());
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8080");
    }

    //  Handles deposit or withdraw transaction 
    private String handleTransaction(HttpExchange exchange, String operation) throws IOException {
        String response = "";
        try {
            String query = exchange.getRequestURI().getQuery();
            if (query != null && query.contains("amount=")) {
                String amountStr = query.split("amount=")[1];
                BigDecimal amount = new BigDecimal(amountStr);

                if ("deposit".equals(operation)) {
                    bankAccountService.deposit(amount);
                    response = "Deposit of " + amount + " EUR was successful.";
                } else if ("withdraw".equals(operation)) {
                    bankAccountService.withdraw(amount);
                    response = "Withdrawal of " + amount + " EUR was successful.";
                }
            } else {
                response = "Amount parameter is missing.";
            }
        } catch (NumberFormatException e) {
            response = "Error: Invalid amount format.";
        } catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        return response;
    }

    // Sends HTTP response
    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.sendResponseHeaders(200, response.getBytes().length);
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

}
