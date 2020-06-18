package ru.otus.main;

import com.google.gson.Gson;
import ru.otus.pojo.MyGson;
import ru.otus.pojo.Person;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        List<String> friendList  = new ArrayList<>();
        friendList.add("Vasya");
        friendList.add("Lena");
        HashMap<String,Integer> asdsad = new HashMap<>();
        asdsad.put("dsadsa", 234);
        asdsad.put("dsadsa", 235);
        int[] ages = {1, 2, 3 , 4, 5};

        String[] clothes = new String[]{"short", "pants"};
        Person person = new Person(15, friendList, clothes, asdsad, ages, "PETRO");
        MyGson myGson = new MyGson();
        myGson.toJson(person);
        Gson gson = new Gson();
        String json = gson.toJson(person);
        System.out.println("-------------------------------------------------------------");
        System.out.println(json);
    }
}
