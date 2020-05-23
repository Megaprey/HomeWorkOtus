package ru.otus.pojo;

public class FiftyBanknoteContainer extends BanknoteContainer{
    private final static int denomination = 50;

    public FiftyBanknoteContainer(int number, ATM aTM){
        this.number = number;
        this.aTM = aTM;
        this.denominationName = "50 рублей";
    }

        public int getDenomination() {
            return denomination;
    }
}
