package com.bankkata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.bankkata.service.BankAccountService;
import com.bankkata.service.BankAccountServiceImpl;
import com.bankkata.view.SimpleTransactionFormatter;

public class WithdrawTest {
    
    @Test
    void withdrawDecreasesBalance() {
        BankAccountService bankService = new BankAccountServiceImpl("Alice", BigDecimal.valueOf(200), "Europe/Paris", new SimpleTransactionFormatter());
        bankService.withdraw(new BigDecimal("50"));
        assertEquals(new BigDecimal("150"), ((BankAccountServiceImpl) bankService).getBankAccount().getBalance());
    }

    @Test
    void withdrawMoreThanBalanceThrowsException() {
        BankAccountService bankService = new BankAccountServiceImpl("Alice", BigDecimal.valueOf(100), "Europe/Paris", new SimpleTransactionFormatter());
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> bankService.withdraw(new BigDecimal("150"))
        );
        assertEquals("Not enough balance to withdraw", exception.getMessage());
    }

    @Test
    void withdrawNegativeAmountThrowsException() {
        BankAccountService bankService = new BankAccountServiceImpl("Alice", BigDecimal.valueOf(100), "Europe/Paris", new SimpleTransactionFormatter());
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> bankService.withdraw(new BigDecimal("-50"))
        );
        assertEquals("Amount must be positive", exception.getMessage());
    }
}
