package ru.otus.pojo;

public interface Container {
    public void addBanknote(int number);
    public int subtractBanknote(int number);
    public int getDenomination();
    public String getDenominationName();
}
