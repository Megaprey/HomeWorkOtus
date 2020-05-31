package ru.otus.pojo;

public class BanknoteContainer implements Container, Comparable{
    int number = 0;
    Denomination denomination;

    public void addBanknote(int number){
        this.number += number;
    }

    public int subtractBanknote(int number) {
        if (this.number < number) {
            return this.number;
        }
        this.number -= number;
        return number;
    }
    public int getDenomination() {
        return denomination.getDenominationValue();
    }

    @Override
    public String getDenominationName() {
        return denomination.getDenominationName();
    }

    public BanknoteContainer(int number, Denomination denomination){
        this.number = number;
        this.denomination = denomination;
    }

    @Override
    public String toString() {
        return "Количество купюр номинала в " + getDenominationName() + " равно " + number;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof BanknoteContainer)) {
            throw new IllegalArgumentException("разные объекты");
        }
        BanknoteContainer withContainer = (BanknoteContainer) o;
        if(getDenomination() > withContainer.getDenomination()) {
            return -1;
        }
        if(getDenomination() < withContainer.getDenomination()) {
            return 1;
        }
        return 0;

    }
}
