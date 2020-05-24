package ru.otus.pojo;

import java.util.Set;
import java.util.TreeSet;

public class ATM implements ATMEmulator{

    private Set<Container> containers;

    private int amount;

    public ATM(int fiftyBanknoteNumber, int oneHundredBanknoteNumber, int fiveHundredBunknoteNumber,
               int oneThousandBanknoteNumber, int fiveThousandBanknoteNumber){
        containers = new TreeSet<>();
        containers.add(new BanknoteContainer(fiftyBanknoteNumber, this, Denomination.FIFTY));
        containers.add(new BanknoteContainer(oneHundredBanknoteNumber, this, Denomination.ONE_HUNDRED));
        containers.add(new BanknoteContainer(fiveHundredBunknoteNumber, this, Denomination.FIVE_HUNDRED));
        containers.add(new BanknoteContainer(oneThousandBanknoteNumber, this, Denomination.ONE_THOUSAND));
        containers.add(new BanknoteContainer(fiveThousandBanknoteNumber, this, Denomination.FIVE_THOUSEND));
    }

    public void withdrawBalance(){
        System.out.println("Остаток в банкомате:");
        containers.forEach(container -> System.out.println(container));
    }

    public void withdrawCash(int amount) throws TakeMoneyException {
        this.amount = amount;
        containers.forEach(container -> {
            int numberBanknote = takeMaxBanknoteFromAmount(container);
            System.out.println("Выплатили купюр номиналом в " + container.getDenominationName() +
                    " равно " + numberBanknote);

        });

        if (this.amount != 0) {
            throw new TakeMoneyException();
        }
    }

    private int takeMaxBanknoteFromAmount(Container container) {
        int numberBanknote = amount / container.getDenomination();
        int banknotes = container.subtractBanknote(numberBanknote);
        amount = amount - banknotes * container.getDenomination();
        return banknotes;
    }
}
