package com.bankkata;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.bankkata.service.BankAccountService;
import com.bankkata.service.BankAccountServiceImpl;
import com.bankkata.view.SimpleTransactionFormatter;

public class validateAmountTest {

    @Test
    void validateNegativeAmountThrowsException() {
        BankAccountService bankService = new BankAccountServiceImpl("Alice", BigDecimal.ZERO, "Europe/Paris", new SimpleTransactionFormatter());
        assertThrows(
            IllegalArgumentException.class,
            () -> bankService.deposit(new BigDecimal("-100"))
        );
    }

    @Test
    void validateZeroAmountThrowsException() {
        BankAccountService bankService = new BankAccountServiceImpl("Alice", BigDecimal.ZERO, "Europe/Paris", new SimpleTransactionFormatter());
        assertThrows(
            IllegalArgumentException.class,
            () -> bankService.deposit(BigDecimal.ZERO)
        );
    }
    
}
