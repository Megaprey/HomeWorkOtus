package ru.otus.pojo;

public interface ATMEmulator {
    void withdrawBalance();
    void withdrawCash(int amount) throws TakeMoneyException;
}
