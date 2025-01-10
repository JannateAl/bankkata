package com.bankkata;

import java.math.BigDecimal;

import com.bankkata.service.BankAccountService;
import com.bankkata.service.BankAccountServiceImpl;

public class BankApp 
{
    public static void main(String[] args) {
        // Create bank account with initial balance
        BankAccountService mybankService = new BankAccountServiceImpl("Alice", new BigDecimal("150"));

        // Perform some operations
        mybankService.deposit(new BigDecimal("300"));
        mybankService.withdraw(new BigDecimal("100"));
        mybankService.deposit(new BigDecimal("400"));
        mybankService.withdraw(new BigDecimal("200"));

        // Display account statement
        mybankService.printStatement();

        // Display 
        System.out.println("\nFinal balance : " + ((BankAccountServiceImpl) mybankService).getBankAccount().getBalance() + " EUR");
    }
}
