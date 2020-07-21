package ru.otus.core.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressDataSet {

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "id=" + id +
                ", street='" + street + '\'' +
                '}';
    }

    private int id;

    @Id
    public int getId() {
        return id;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    @Column(name = "street")
    private String street;

    public AddressDataSet(String street) {
        this.street = street;
    }
}
