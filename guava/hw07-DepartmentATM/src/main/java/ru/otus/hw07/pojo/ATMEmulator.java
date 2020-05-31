package ru.otus.hw07.pojo;

import java.util.Set;
import java.util.TreeSet;

public class ATMEmulator implements ATM {
    private ATM nextATM;
    private Set<Container> containers = new TreeSet<>();;

    private int amount;

    public void setNextATM(ATM nextATM) {
        this.nextATM = nextATM;
    }

    public ATMState saveState() {
        ATMState atmState = new ATMState();
        atmState.setContainers(containers);
        return atmState;
    }

    public void loadState(ATMState atmState) {
        containers = atmState.getContainers();
    }

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
        int numberBanknote = amount / container.getDenominationValue();
        int banknotes = container.subtractBanknote(numberBanknote);
        amount = amount - banknotes * container.getDenominationValue();
        return banknotes;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public int getNumberBanknoteForDenomination(Denomination denomination) {
        int number = 0;
        for (Container container:containers) {
            if (container.getDenomination().equals(denomination)){
                number = container.getNumber();
            }
        }
        if(nextATM != null) {
            number += nextATM.getNumberBanknoteForDenomination(denomination);
        }
        return number;
    }
}
