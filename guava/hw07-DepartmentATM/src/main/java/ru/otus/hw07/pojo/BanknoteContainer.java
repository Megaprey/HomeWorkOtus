package ru.otus.hw07.pojo;

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

    public Denomination getDenomination() {
        return denomination;
    }

    public int getDenominationValue() {
        return denomination.getDenominationValue();
    }

    @Override
    public String getDenominationName() {
        return denomination.getDenominationName();
    }

    @Override
    public BanknoteContainer copy() {
        try {
            return (BanknoteContainer)this.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
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
        if(getDenominationValue() > withContainer.getDenominationValue()) {
            return -1;
        }
        if(getDenominationValue() < withContainer.getDenominationValue()) {
            return 1;
        }
        return 0;

    }

    public int getNumber() {
        return number;
    }
}
