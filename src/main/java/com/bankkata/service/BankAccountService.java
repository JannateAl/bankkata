package com.bankkata.service;

import java.math.BigDecimal;

public interface BankAccountService {

    void deposit(BigDecimal amount);
    void withdraw(BigDecimal amount);
    String printStatement();

}
