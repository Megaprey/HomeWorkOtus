package ru.otus.main;

import ru.otus.pojo.ATM;
import ru.otus.pojo.TakeMoneyException;

public class Main {
    public static void main(String[] args) throws TakeMoneyException {
        ATM atm = new ATM(10,10, 10, 10,
                10);
        atm.withdrawCash(33050);
        atm.withdrawBalance();
    }
}