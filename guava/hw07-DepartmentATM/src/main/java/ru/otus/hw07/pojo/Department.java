package ru.otus.hw07.pojo;

import java.util.ArrayList;
import java.util.List;

public class Department {
    List<ATM> listAtm = new ArrayList<>();
    List<ATMState> registry = new ArrayList<>();

    public void addATM(ATM atm){
        int size = listAtm.size();
        if (size != 0) {
            listAtm.get(size - 1).setNextATM(atm);
        }
        registry.add(atm.saveState());
        listAtm.add(atm);

    }

    public void printBalanceAllDepartments() {
        System.out.println("Остаток в банкоматах:");
        for (Denomination denomination : Denomination.values()) {
            System.out.println("Количество купюр номинала в " + denomination.getDenominationName() + " равно " +
                    listAtm.get(0).getNumberBanknoteForDenomination(denomination));
        }
    }
    public void returnFirstStateAllATM(){
        for (int i = 0; i < listAtm.size(); i++) {
            listAtm.get(i).loadState(registry.get(i));
        }
    }

}
