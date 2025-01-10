package com.bankkata;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private final Operation operation;
    private final double montant;
    private final double balanceAfterOper;
    private final LocalDate date;

    public Transaction(Operation operation, double montant, double balanceAfterOper) {
        this.operation = operation;
        this.montant = montant;
        this.balanceAfterOper = balanceAfterOper;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return operation + " | " + montant + " | " + balanceAfterOper  + " | " + date.format(formatter) ;
    }
    
}
