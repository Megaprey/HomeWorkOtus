package ru.otus.pojo;

public class OneHundredBanknoteContainer extends BanknoteContainer{
    private final static int denomination = 100;

    public OneHundredBanknoteContainer(int number, ATM aTM){
        this.number = number;
        this.aTM = aTM;
        this.denominationName = "100 рублей";
    }

    public int getDenomination() {
        return denomination;
    }
}
