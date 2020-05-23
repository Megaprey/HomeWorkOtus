package ru.otus.pojo;

public class FiveHundredBanknoteContainer extends BanknoteContainer{
    private final static int denomination = 500;

    public FiveHundredBanknoteContainer(int number, ATM aTM){
        this.number = number;
        this.aTM = aTM;
        this.denominationName = "500 рублей";
    }

    public int getDenomination() {
        return denomination;
    }
}
