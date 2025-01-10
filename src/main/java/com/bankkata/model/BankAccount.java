package com.bankkata.model;
   
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private BigDecimal balance;
    private final List<Transaction> transactions;
    private final String bankClient;
    
    public BankAccount(String bankClient, BigDecimal initialBalance) {
        if (initialBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Initial balance must be positive.");
        }
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        this.bankClient = bankClient;
    }

    public String getBankClient() {
        return bankClient;
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
    
}
