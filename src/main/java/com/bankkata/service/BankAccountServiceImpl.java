package com.bankkata.service;

import java.math.BigDecimal;

import com.bankkata.model.BankAccount;
import com.bankkata.model.Operation;
import com.bankkata.model.Transaction;
import com.bankkata.utils.LoggerUtil;
import com.bankkata.view.TransactionFormatter;

/**
 * Implementation of the BankAccountService interface.
 * Manages deposit, withdrawal, and account statement operations for a bank account.
 */

public class BankAccountServiceImpl implements BankAccountService{

    private final BankAccount bankAccount;
    private final TransactionFormatter transactionFormatter;

    public BankAccountServiceImpl(String clientName, BigDecimal initialAmount, String clienZone, TransactionFormatter formatter) {
        this.bankAccount = new BankAccount(clientName, initialAmount, clienZone);
        this.transactionFormatter = formatter;
    }

    @Override
    public void deposit(BigDecimal amount) {
        validateAmount(amount);
        bankAccount.setBalance(bankAccount.getBalance().add(amount));
        bankAccount.getTransactions().add(new Transaction(Operation.DEPOSIT, amount, bankAccount.getBalance()));
        LoggerUtil.logInfo("Deposit was successful: " + amount);
    }

    @Override
    public void withdraw(BigDecimal amount) {
        validateAmount(amount);
        if (amount.compareTo(bankAccount.getBalance()) > 0) {
            LoggerUtil.logError("Attempted to withdraw with insufficient funds.");
            throw new IllegalArgumentException("Not enough balance to withdraw");
        }
        bankAccount.setBalance(bankAccount.getBalance().subtract(amount));;
        bankAccount.getTransactions().add(new Transaction(Operation.WITHDRAW, amount, bankAccount.getBalance()));
        LoggerUtil.logInfo("Withdraw was successful: " + amount);
    }

    @Override
    public String printStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("-----------------------------------------\n");
        statement.append("\n+++ Account Statement for: " + bankAccount.getClientName() + " +++\n");
        statement.append("-----------------------------------------\n");
        statement.append("OPERATION | MONTANT | BALANCE | DATE\n");
        
        for (Transaction transaction : bankAccount.getTransactions()) {
            statement.append(transactionFormatter.format(transaction, bankAccount.getClientZone())).append("\n");
        }
        statement.append("\nFinal balance : " + bankAccount.getBalance() + " EUR");
        return statement.toString();
    }

    public void validateAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            LoggerUtil.logError("Attempted operation with invalid amount: " + amount);
            throw new IllegalArgumentException("Amount must be positive");
        }
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

}
