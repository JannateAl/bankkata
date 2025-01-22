package com.bankkata;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.bankkata.service.BankAccountService;
import com.bankkata.service.BankAccountServiceImpl;
import com.bankkata.view.SimpleTransactionFormatter;

public class printStatementTest {

    @Test
    void statementIncludesAllTransactions() {
        BankAccountService bankService = new BankAccountServiceImpl("Alice", BigDecimal.valueOf(1000), "Europe/Paris", new SimpleTransactionFormatter());
        bankService.deposit(new BigDecimal("200"));
        bankService.withdraw(new BigDecimal("50"));

        String statement = bankService.printStatement();

        assertTrue(statement.contains("DEPOSIT"));
        assertTrue(statement.contains("200"));
        assertTrue(statement.contains("WITHDRAW"));
        assertTrue(statement.contains("50"));
        assertTrue(statement.contains("1150")); // Final balance
    }
    
}
