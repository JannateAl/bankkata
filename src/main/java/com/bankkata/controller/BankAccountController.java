package com.bankkata.controller;

import com.bankkata.service.BankAccountService;
import com.bankkata.service.BankAccountServiceImpl;
import com.bankkata.utils.RequestParamExtractor;
import com.bankkata.utils.ResponseHandler;
import com.bankkata.view.SimpleTransactionFormatter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.InetSocketAddress;


public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(String clientName, BigDecimal initialAmount, String clientZone) {
        this.bankAccountService = new BankAccountServiceImpl(clientName, initialAmount, clientZone, new SimpleTransactionFormatter());
    }

    // Starts the HTTP server to listen for client requests
    public void startServer() throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        
        /**
         * Handles deposit requests
         * Expects a GET request with the request parameter "amount"
         */
        server.createContext("/deposit", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                    ResponseHandler.sendErrorResponse(exchange, 405, "Method Not Allowed");
                    return;
                }
                
                try {
                    BigDecimal amount = RequestParamExtractor.extractAmount(exchange);
                    bankAccountService.deposit(amount);
                    ResponseHandler.sendResponse(exchange, 200, "Deposit was successful: " + amount + " EUR");
                } catch (IllegalArgumentException e) {
                    ResponseHandler.sendErrorResponse(exchange, 400, e.getMessage());
                } catch (Exception e) {
                    ResponseHandler.sendErrorResponse(exchange, 500, "Internal Server Error: " + e.getMessage());
                }
            }
        });

        /**
         * Handles withdraw requests
         * Expects a GET request with the request parameter "amount"
         */
        server.createContext("/withdraw", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                    ResponseHandler.sendErrorResponse(exchange, 405, "Method Not Allowed");
                    return;
                }

                try {
                    BigDecimal amount = RequestParamExtractor.extractAmount(exchange);
                    bankAccountService.withdraw(amount);
                    ResponseHandler.sendResponse(exchange, 200, "Withdrawal was successful: " + amount + " EUR");
                } catch (IllegalArgumentException e) {
                    ResponseHandler.sendErrorResponse(exchange, 400, e.getMessage());
                } catch (Exception e) {
                    ResponseHandler.sendErrorResponse(exchange, 500, "Internal Server Error: " + e.getMessage());
                }
            }
        });

        /**
         * Handles account statement requests
         * Expects a GET request
         */
        server.createContext("/statement", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                    ResponseHandler.sendErrorResponse(exchange, 405, "Method Not Allowed");
                    return;
                }
                
                try {
                    String statement = bankAccountService.printStatement();
                    ResponseHandler.sendResponse(exchange, 200, statement);
                } catch (Exception e) {
                    ResponseHandler.sendErrorResponse(exchange, 500, "Internal Server Error: " + e.getMessage());
                }
            }
        });

        server.setExecutor(null);
        server.start();
        System.out.println("Server started on port 8081");
    }


}