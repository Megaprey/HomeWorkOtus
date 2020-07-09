package ru.otus.main;

import com.google.gson.Gson;
import ru.otus.pojo.MyGson;
import ru.otus.pojo.Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        var gson = new Gson();
        MyGson myGson = new MyGson();

        List<String> friendList  = new ArrayList<>();
        friendList.add("Vasya");
        friendList.add("Lena");
        HashMap<String,Integer> asdsad = new HashMap<>();
        asdsad.put("dsadsa", 234);
        asdsad.put("dsadsa", 235);
        int[] ages = {1, 2, 3 , 4, 5};

        String[] clothes = new String[]{"short", "pants"};
        Person person = new Person(15, friendList, clothes, asdsad, ages, "PETRO");
        String myJson = myGson.toJson(person);
        System.out.println(myJson);
        Person person1 = gson.fromJson(myJson, Person.class);
        System.out.println(person.equals(person1));

    }
}
