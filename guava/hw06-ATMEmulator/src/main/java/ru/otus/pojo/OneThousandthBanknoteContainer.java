package ru.otus.pojo;

public class OneThousandthBanknoteContainer extends BanknoteContainer{
    private final static int denomination = 1000;

    public OneThousandthBanknoteContainer(int number, ATM aTM){
        this.number = number;
        this.aTM = aTM;
        this.denominationName = "1000 рублей";
    }

    public int getDenomination() {
        return denomination;
    }
}
