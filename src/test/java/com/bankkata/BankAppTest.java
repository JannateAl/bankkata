package com.bankkata;

import org.junit.jupiter.api.Test;

import com.bankkata.model.BankAccount;
import com.bankkata.service.BankAccountService;
import com.bankkata.service.BankAccountServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;


public class BankAppTest 
{

    @Test
    public void depositTest() {
        BankAccountService bankService = new BankAccountServiceImpl("Bob", new BigDecimal("50"));
        bankService.deposit(new BigDecimal("100"));
        assertEquals(new BigDecimal("150"), ((BankAccountServiceImpl) bankService).getBankAccount().getBalance());
    }

    @Test
    public void withdrawTest() {
        BankAccountService bankService = new BankAccountServiceImpl("Bob", new BigDecimal("200"));
        bankService.withdraw(new BigDecimal("150"));
        assertEquals(new BigDecimal("50"), ((BankAccountServiceImpl) bankService).getBankAccount().getBalance());
    }

    @Test
    public void negativeDepositTest() {
        BankAccountService bankService = new BankAccountServiceImpl("Bob", new BigDecimal("100"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> bankService.deposit(new BigDecimal("-50")));
        assertEquals("Amount must be positive", exception.getMessage());
    }

    @Test
    public void balanceNotSufficientTest() {
        BankAccountService bankService = new BankAccountServiceImpl("Bob", new BigDecimal("50"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> bankService.withdraw(new BigDecimal("100")));
        assertEquals("Not enough balance to withdraw", exception.getMessage());
    }

    @Test
    public void testPrintStatement() {
        BankAccountService bankService = new BankAccountServiceImpl("Bob", new BigDecimal("150"));
        bankService.deposit(new BigDecimal("500"));
        bankService.withdraw(new BigDecimal("200"));
        bankService.printStatement();
    }


}
