package ru.otus.pojo;

public class FiveThousandBanknoteContainer extends BanknoteContainer{
    private final static int denomination = 5000;

    public FiveThousandBanknoteContainer(int number, ATM aTM){
        this.number = number;
        this.aTM = aTM;
        this.denominationName = "5000 рублей";
    }

    public int getDenomination() {
        return denomination;
    }
}
