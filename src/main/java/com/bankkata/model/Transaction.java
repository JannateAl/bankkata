package com.bankkata.model;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Represents a financial transaction (deposit or withdrawal) in a bank account.
 * Stores the operation type, amount, balance after the operation, and the date of the transaction.
 */

public class Transaction {

    private final Operation operation;
    private final BigDecimal amount;
    private final BigDecimal balanceAfterOper;
    private final ZonedDateTime date;

    public Transaction(Operation operation, BigDecimal amount, BigDecimal balanceAfterOper) {
        this.operation = operation;
        this.amount = amount;
        this.balanceAfterOper = balanceAfterOper;
        this.date = ZonedDateTime.now(ZoneId.of("UTC"));
    }

    public Operation getOperation() {
        return operation;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getBalanceAfterOper() {
        return balanceAfterOper;
    }

    public ZonedDateTime getDate() {
        return date;
    }

}
