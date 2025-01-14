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
    public String printStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("-----------------------------------------\n");
        statement.append("\n+++ Account Statement for: " + bankAccount.getBankClient() + " +++\n");
        statement.append("-----------------------------------------\n");
        statement.append("OPERATION | MONTANT | BALANCE | DATE\n");
        
        for (Transaction transaction : bankAccount.getTransactions()) {
            statement.append(transaction.toString() + "\n");
        }
        statement.append("\nFinal balance : " + bankAccount.getBalance() + " EUR");
        return statement.toString();
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
