package ru.otus.main;

import ru.otus.pojo.ATMEmulator;
import ru.otus.pojo.BanknoteContainer;
import ru.otus.pojo.Denomination;
import ru.otus.pojo.TakeMoneyException;

public class Main {
    public static void main(String[] args) throws TakeMoneyException {
        ATMEmulator atm = new ATMEmulator();

        atm.addBanknoteContainerWithMoney(10, Denomination.FIFTY);
        atm.addBanknoteContainerWithMoney(10, Denomination.ONE_HUNDRED);
        atm.addBanknoteContainerWithMoney(10, Denomination.FIVE_HUNDRED);
        atm.addBanknoteContainerWithMoney(10, Denomination.FIVE_THOUSEND);
        atm.addBanknoteContainerWithMoney(10, Denomination.ONE_THOUSAND);
        atm.withdrawCash(33050);
        atm.withdrawBalance();
    }
}