package com.example;

public class BankAccount {
    private double balance;

    public BankAccount(double initialAmount) {
        this.balance = initialAmount;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new NegativeAmountException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposited: ₹" + amount);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new NegativeAmountException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance.");
        }
        balance -= amount;
        System.out.println("Withdrawn: ₹" + amount);
    }

    public double getBalance() {
        return balance;
    }
}