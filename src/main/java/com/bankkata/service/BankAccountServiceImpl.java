package com.bankkata.service;

import java.math.BigDecimal;

import com.bankkata.model.BankAccount;
import com.bankkata.model.Operation;
import com.bankkata.model.Transaction;

public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccount bankAccount;

    public BankAccountServiceImpl(String client, BigDecimal initialAmount) {
        this.bankAccount = new BankAccount(client, initialAmount);
    }

    @Override
    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        bankAccount.getTransactions().add(new Transaction(Operation.DEPOSIT, amount, bankAccount.getBalance()));
        System.out.println("Deposit was successful: " + amount + " EUR");
    }

    @Override
    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        if (amount.compareTo(bankAccount.getBalance()) > 0) {
            throw new IllegalArgumentException("Not enough balance to withdraw");
        }
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));;
        bankAccount.getTransactions().add(new Transaction(Operation.WITHDRAW, amount, bankAccount.getBalance()));
        System.out.println("Withdraw was successful: " + amount + " EUR");
    }

    @Override
    public void printStatement() {
        System.out.println("-----------------------------------------");
        System.out.println("\n+++ Account Statement for: " + bankAccount.getBankClient() + " +++");
        System.out.println("-----------------------------------------");
        System.out.println("OPERATION | MONTANT | BALANCE | DATE");
        for (Transaction transaction : bankAccount.getTransactions()) {
            System.out.println(transaction.toString());
        }
    }

    public void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

}
