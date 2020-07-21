package ru.otus.core.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class PhoneDataSet {
    private int id;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Column(name = "number")
    private String number;

    public PhoneDataSet() {
    }

    @Id
    public int getId() {
        return id;
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
