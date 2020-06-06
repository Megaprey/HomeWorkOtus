package ru.otus.hw07.pojo;

public interface Container {
    public void addBanknote(int number);
    public int subtractBanknote(int number);
    public int getDenominationValue();
    public Denomination getDenomination();
    public int getNumber();
    public String getDenominationName();

    Container copy();
}
