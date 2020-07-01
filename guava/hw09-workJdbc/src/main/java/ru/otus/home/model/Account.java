package ru.otus.home.model;

import ru.otus.jdbc.mapper.Id;

public class Account {
    @Id
    private int id;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }

    private String type;
    private int rest;

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

}
