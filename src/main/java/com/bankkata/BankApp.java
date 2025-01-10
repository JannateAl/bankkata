package com.bankkata;

public class BankApp 
{
    public static void main(String[] args) {
        // Create un bank account avec un solde initial
        BankAccount myAccount = new BankAccount("Alice", 150);

        // Effectuer des opérations sur le compte
        myAccount.deposit(300);
        myAccount.withdraw(100);
        myAccount.deposit(400);
        myAccount.withdraw(200);

        // Display le relevé complet
        System.out.println("\n+++ Historique des transactions +++");
        myAccount.printStatement();

        // Display du solde final
        System.out.println("\nSolde final : " + myAccount.getBalance() + " EUR");
    }
}
