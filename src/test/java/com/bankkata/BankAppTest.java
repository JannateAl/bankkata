package com.bankkata;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BankAppTest 
{

    @Test
    public void depositTest() {
        BankAccount account = new BankAccount("Bob", 50);
        account.deposit(100);
        assertEquals(150, account.getBalance());
    }

    @Test
    public void withdrawTest() {
        BankAccount account = new BankAccount("Bob", 200);
        account.withdraw(150);
        assertEquals(50, account.getBalance());
    }

    @Test
    public void negativeDepositTest() {
        BankAccount account = new BankAccount("Bob", 100);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.deposit(-50));
        assertEquals("Montant must be positive", exception.getMessage());
    }

    @Test
    public void testPrintStatement() {
        BankAccount account = new BankAccount("Bob", 150);
        account.deposit(500);
        account.withdraw(200);
        account.printStatement();
    }


}
