package com.bankkata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.bankkata.service.BankAccountService;
import com.bankkata.service.BankAccountServiceImpl;
import com.bankkata.view.SimpleTransactionFormatter;

public class DepositTest {

    @Test
    void depositIncreasesBalance() {
        BankAccountService bankService = new BankAccountServiceImpl("Alice", BigDecimal.ZERO, "Europe/Paris", new SimpleTransactionFormatter());
        bankService.deposit(new BigDecimal("100"));
        assertEquals(new BigDecimal("100"), ((BankAccountServiceImpl) bankService).getBankAccount().getBalance());
    }

    @Test
    void negativeDepositThrowsException() {
        BankAccountService bankService = new BankAccountServiceImpl("Alice", BigDecimal.ZERO, "Europe/Paris", new SimpleTransactionFormatter());
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> bankService.deposit(new BigDecimal("-50"))
        );
        assertEquals("Amount must be positive", exception.getMessage());
    }

    @Test
    void zeroDepositThrowsException() {
        BankAccountService bankService = new BankAccountServiceImpl("Alice", BigDecimal.ZERO, "Europe/Paris", new SimpleTransactionFormatter());
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> bankService.deposit(BigDecimal.ZERO)
        );
        assertEquals("Amount must be positive", exception.getMessage());
    }
    
}
