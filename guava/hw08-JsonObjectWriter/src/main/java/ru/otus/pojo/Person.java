package ru.otus.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Person {
    int age;
    List<String> friendNames = new ArrayList<String>();
    String[] clothes;
    Map<String, Integer > clothes2;
    int[] ages;
    String name;

    public Person(int age, List<String> friendNames, String[] clothes, Map<String, Integer> clothes2, int[] ages, String name) {
        this.age = age;
        this.friendNames = friendNames;
        this.clothes = clothes;
        this.clothes2 = clothes2;
        this.ages = ages;
        this.name = name;
    }
}
