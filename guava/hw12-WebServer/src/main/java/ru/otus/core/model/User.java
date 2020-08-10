package ru.otus.core.model;

/**
 * @author sergey
 * created on 03.02.19.
 */
import ru.otus.jdbc.mapper.Id;

public class User {
    @Id    private int id;

    public String getLogin() {
        return login;
    }

    private String login;
    private String pass;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getPass() {
        return pass;
    }

    public User(int id, String name, int age, String login, String pass) {
        this.id = id;
        this.login = login;
        this.pass = pass;
        this.name = name;
        this.age = age;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", login='" + login + '\'' +
                ", pass=" + pass +
                '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }
}
