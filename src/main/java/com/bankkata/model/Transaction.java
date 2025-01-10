package com.bankkata.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final Operation operation;
    private final BigDecimal amount;
    private final BigDecimal balanceAfterOper;
    private final LocalDate date;

    public Transaction(Operation operation, BigDecimal amount, BigDecimal balanceAfterOper) {
        this.operation = operation;
        this.amount = amount;
        this.balanceAfterOper = balanceAfterOper;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return operation + " | " + amount + " | " + balanceAfterOper  + " | " + date.format(formatter) ;
    }
    
}
