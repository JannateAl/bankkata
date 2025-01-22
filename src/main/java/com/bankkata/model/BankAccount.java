package com.bankkata.model;
   
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank account with basic attributes such as balance,
 * client information, and a list of transactions.
 */

public class BankAccount {

    private BigDecimal balance;
    private final List<Transaction> transactions;
    private final String clientName;
    private final String clientZone;
    
    public BankAccount(String clientName, BigDecimal initialBalance, String clientZone) {
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance must be positive.");
        }
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        this.clientName = clientName;
        this.clientZone = clientZone != null ? clientZone : "UTC";
    }

    public String getClientName() {
        return clientName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getClientZone() {
        return clientZone;
    }
    
}
