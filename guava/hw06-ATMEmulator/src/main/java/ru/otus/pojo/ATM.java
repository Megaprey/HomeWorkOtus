package ru.otus.pojo;

public interface ATM {
    void withdrawBalance();
    void withdrawCash(int amount) throws TakeMoneyException;
}
