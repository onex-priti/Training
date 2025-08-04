package com.example;

public class BankTransactionDemo {

    // Try-with-resources to log transaction
    public static void logTransaction(String message) {
        try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("bank_log.txt", true))) {
            writer.write(message + "\n");
        } catch (java.io.IOException e) {
            System.out.println("Logging failed: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000.0);

        try {
            account.deposit(500);
            logTransaction("Deposited ₹500");

            account.withdraw(200);
            logTransaction("Withdrawn ₹200");

            // This will trigger a checked exception
            account.withdraw(2000);
            logTransaction("Attempted to withdraw ₹2000");

        } catch (InsufficientFundsException e) {
            System.out.println("Checked Exception: " + e.getMessage());
            logTransaction("Failed withdrawal: " + e.getMessage());

        } catch (NegativeAmountException e) {
            System.out.println("Unchecked Exception: " + e.getMessage());
            logTransaction("Invalid transaction: " + e.getMessage());

        } finally {
            System.out.println("Final balance: ₹" + account.getBalance());
            logTransaction("Final balance: ₹" + account.getBalance());
        }
    }
}
