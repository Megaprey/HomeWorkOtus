package ru.otus.pojo;

public abstract class BanknoteContainer implements Container{
    int number = 0;
    ATM aTM;
    String denominationName = "";

    public void addBanknote(int number){
        this.number += number;
    }

    public void subtractBanknote(int number){
        this.number -= number;
    }

    @Override
    public String toString() {
        return "Количество купюр номинала в " + denominationName + " равно " + number;
    }
}
