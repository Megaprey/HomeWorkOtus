package ru.otus.hw07.pojo;

public interface ATM {
    int getNumberBanknoteForDenomination(Denomination denomination);
    void withdrawBalance();
    void withdrawCash(int amount) throws TakeMoneyException;
    void setNextATM(ATM nextATM);
    ATMState saveState();
    void loadState(ATMState atmState);
}
