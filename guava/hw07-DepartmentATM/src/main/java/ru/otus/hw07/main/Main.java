package ru.otus.hw07.main;

import ru.otus.hw07.pojo.ATMEmulator;
import ru.otus.hw07.pojo.Denomination;
import ru.otus.hw07.pojo.Department;
import ru.otus.hw07.pojo.TakeMoneyException;

public class Main {
    public static void main(String[] args) throws TakeMoneyException {
        ATMEmulator atm = new ATMEmulator();

        atm.addBanknoteContainerWithMoney(10, Denomination.FIFTY);
        atm.addBanknoteContainerWithMoney(10, Denomination.ONE_HUNDRED);
        atm.addBanknoteContainerWithMoney(10, Denomination.FIVE_HUNDRED);
        atm.addBanknoteContainerWithMoney(10, Denomination.FIVE_THOUSEND);
        atm.addBanknoteContainerWithMoney(10, Denomination.ONE_THOUSAND);
        atm.withdrawBalance();

        ATMEmulator atm2 = new ATMEmulator();

        atm2.addBanknoteContainerWithMoney(15, Denomination.FIFTY);
        atm2.addBanknoteContainerWithMoney(15, Denomination.ONE_HUNDRED);
        atm2.addBanknoteContainerWithMoney(15, Denomination.FIVE_HUNDRED);
        atm2.addBanknoteContainerWithMoney(15, Denomination.FIVE_THOUSEND);
        atm2.addBanknoteContainerWithMoney(15, Denomination.ONE_THOUSAND);
        atm2.withdrawBalance();

        Department department = new Department();
        department.addATM(atm);
        department.addATM(atm2);
        department.printBalanceAllDepartments();
        System.out.println("Василию из первого банкомата:");
        atm.withdrawCash(33050);
        System.out.println("Анне из второго банкомата:");
        atm2.withdrawCash(73550);
        department.printBalanceAllDepartments();
        System.out.println("Банкоматы вернули в изначальное состояние");
        department.returnFirstStateAllATM();
        department.printBalanceAllDepartments();


    }
}