package com.bankkata;

import java.io.IOException;
import java.math.BigDecimal;

import com.bankkata.controller.BankAccountController;

public class BankApp 
{
    public static void main(String[] args) throws IOException {
        BankAccountController controller = new BankAccountController("Alice", new BigDecimal("150"));
        controller.startServer();
    }

}
