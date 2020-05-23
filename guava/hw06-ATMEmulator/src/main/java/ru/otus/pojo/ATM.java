package ru.otus.pojo;

public class ATM {
    private FiftyBanknoteContainer fiftyBanknoteContainer;
    private OneHundredBanknoteContainer oneHundredBanknoteContainer;
    private FiveHundredBanknoteContainer fiveHundredBanknoteContainer;
    private OneThousandthBanknoteContainer oneThousandBanknoteContainer;
    private FiveThousandBanknoteContainer fiveThousandBanknoteContainer;

    private int amount;

    public ATM(int fiftyBanknoteNumber, int oneHundredBanknoteNumber, int fiveHundredBunknoteNumber,
               int oneThousandBanknoteNumber, int fiveThousandBanknoteNumber){
        fiftyBanknoteContainer = new FiftyBanknoteContainer(fiftyBanknoteNumber, this);
        oneHundredBanknoteContainer = new OneHundredBanknoteContainer(oneHundredBanknoteNumber, this);
        fiveHundredBanknoteContainer = new FiveHundredBanknoteContainer(fiveHundredBunknoteNumber, this);
        oneThousandBanknoteContainer = new OneThousandthBanknoteContainer(oneThousandBanknoteNumber, this);
        fiveThousandBanknoteContainer = new FiveThousandBanknoteContainer(fiveThousandBanknoteNumber, this);
    }

    public void withdrawBalance(){
        System.out.println("Остаток в банкомате:");
        System.out.println(fiveThousandBanknoteContainer);
        System.out.println(oneThousandBanknoteContainer);
        System.out.println(fiveHundredBanknoteContainer);
        System.out.println(oneHundredBanknoteContainer);
        System.out.println(fiftyBanknoteContainer);
    }

    public void withdrawCash(int amount) throws TakeMoneyException {
        this.amount = amount;
        int numberFiveThousand = takeMaxBanknoteFromAmount(fiveThousandBanknoteContainer);
        int numberOneThousand = takeMaxBanknoteFromAmount(oneThousandBanknoteContainer);
        int numberFiveHundred = takeMaxBanknoteFromAmount(fiveHundredBanknoteContainer);
        int numberOneHundred = takeMaxBanknoteFromAmount(oneHundredBanknoteContainer);
        int numberFifty = takeMaxBanknoteFromAmount(fiftyBanknoteContainer);
        if (this.amount != 0) {
            throw new TakeMoneyException();
        }
        System.out.println("Выплатили купюр номиналом в " + fiveThousandBanknoteContainer.denominationName +
                " равно " + numberFiveThousand);
        System.out.println("Выплатили купюр номиналом в " + oneThousandBanknoteContainer.denominationName +
                " равно " + numberOneThousand);
        System.out.println("Выплатили купюр номиналом в " + fiveHundredBanknoteContainer.denominationName +
                " равно " + numberFiveHundred);
        System.out.println("Выплатили купюр номиналом в " + oneHundredBanknoteContainer.denominationName +
                " равно " + numberOneHundred);
        System.out.println("Выплатили купюр номиналом в " + fiftyBanknoteContainer.denominationName +
                " равно " + numberFifty);
    }

    private int takeMaxBanknoteFromAmount(Container container) {
        int numberBanknote = amount / container.getDenomination();
        container.subtractBanknote(numberBanknote);
        amount = amount - numberBanknote * container.getDenomination();
        return numberBanknote;
    }
}
