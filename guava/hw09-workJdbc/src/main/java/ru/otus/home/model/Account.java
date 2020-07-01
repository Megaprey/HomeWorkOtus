package ru.otus.home.model;

import ru.otus.jdbc.mapper.Id;

public class Account {
    @Id
    private int id;
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
