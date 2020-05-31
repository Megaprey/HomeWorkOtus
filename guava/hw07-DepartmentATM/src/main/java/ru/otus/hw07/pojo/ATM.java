package ru.otus.hw07.pojo;

public interface ATM {
    public int getNumberBanknoteForDenomination(Denomination denomination);
    void withdrawBalance();
    void withdrawCash(int amount) throws TakeMoneyException;
    public void setNextATM(ATM nextATM);
    public ATMState saveState();
    public void loadState(ATMState atmState);
}
