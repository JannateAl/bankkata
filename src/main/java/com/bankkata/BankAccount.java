package com.bankkata;

import java.util.ArrayList;
import java.util.List;

public class BankAccount {

    private double balance;
    private final List<Transaction> transactions;
    private final String bankClient;
    
    public BankAccount(String bankClient, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance must be positive.");
        }
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        this.bankClient = bankClient;
    }
    
    public void deposit(double montant) {
        validateMontant(montant);
        balance += montant;
        transactions.add(new Transaction(Operation.DEPOSIT, montant, balance));
        System.out.println("Deposit was successful: " + montant + " EUR");
    }

    public void withdraw(double montant) {
        validateMontant(montant);
        if (montant > balance) {
            throw new IllegalArgumentException("Not enough balance to withdraw.");
        }
        balance -= montant;
        transactions.add(new Transaction(Operation.WITHDRAW, montant, balance));
        System.out.println("Withdraw successful: " + montant + " EUR");
    }
    
    public void printStatement() {
        System.out.println("\n+++ Account Statement for: " + bankClient + " +++");
        System.out.println("-----------------------------------------");
        System.out.println("OPERATION | MONTANT | BALANCE | DATE");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }

    public double getBalance() {
        return balance;
    }


    private void validateMontant(double montant) {
        if (montant <= 0) {
            throw new IllegalArgumentException("Montant must be positive");
        }
    }

}
