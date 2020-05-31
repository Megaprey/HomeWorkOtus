package ru.otus.pojo;

import java.util.Set;
import java.util.TreeSet;

public class ATMEmulator implements ATM {

    private Set<Container> containers = new TreeSet<>();;

    private int amount;

    public void withdrawBalance(){
        System.out.println("Остаток в банкомате:");
        containers.forEach(container -> System.out.println(container));
    }

    public void addBanknoteContainerWithMoney(int money, Denomination denomination){
        containers.add(new BanknoteContainer(money, denomination));
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
