package ru.otus.pojo;

public enum Denomination{
    FIFTY(50),
    ONE_HUNDRED(100),
    FIVE_HUNDRED(500),
    ONE_THOUSAND(1000),
    FIVE_THOUSEND(5000);

    private int denominationValue;
    private String denominationName;

    Denomination(int denomination){
        denominationValue = denomination;
        denominationName = denominationValue + " рублей";
    }

    public String getDenominationName() {
        return denominationName;
    }

    public int getDenominationValue() {
        return denominationValue;
    }
}
