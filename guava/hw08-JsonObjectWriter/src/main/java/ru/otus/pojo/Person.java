package ru.otus.pojo;

import java.util.*;

public class Person {
    int age;
    List<String> friendNames = new ArrayList<String>();
    String[] clothes;
    Map<String, Integer > clothes2;
    int[] ages;
    String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                friendNames.equals(person.friendNames) &&
                Arrays.equals(clothes, person.clothes) &&
                clothes2.equals(person.clothes2) &&
                Arrays.equals(ages, person.ages) &&
                name.equals(person.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(age, friendNames, clothes2, name);
        result = 31 * result + Arrays.hashCode(clothes);
        result = 31 * result + Arrays.hashCode(ages);
        return result;
    }

    public Person(int age, List<String> friendNames, String[] clothes, Map<String, Integer> clothes2, int[] ages, String name) {
        this.age = age;
        this.friendNames = friendNames;
        this.clothes = clothes;
        this.clothes2 = clothes2;
        this.ages = ages;
        this.name = name;
    }
}
