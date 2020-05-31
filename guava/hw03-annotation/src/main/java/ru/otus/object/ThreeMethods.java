package ru.otus.object;

import ru.otus.annotation.*;

public class ThreeMethods {

    @Before
    public void showBefore(){
        System.out.println("start method before test ...");
        System.out.println("start method before test ...");
        System.out.println("start method before test ...");
        System.out.println("start method before test ...");
        System.out.println("start method before test ...");
        System.out.println("start method before test ...");
        System.out.println("start method before test ...");
        System.out.println("start method before test ...");
        System.out.println("start method before test ...");
    }

    @Test
    public void showTest(){
        System.out.println("start method test ...");
        System.out.println("start method test ...");
        System.out.println("start method test ...");
        System.out.println("start method test ...");
        System.out.println("start method test ...");
        System.out.println("start method test ...");
        System.out.println("start method test ...");
        System.out.println("start method test ...");
        System.out.println("start method test ...");
    }

    @After
    public void showAfter(){
        System.out.println("start method after test ...");
        System.out.println("start method after test ...");
        System.out.println("start method after test ...");
        System.out.println("start method after test ...");
        System.out.println("start method after test ...");
        System.out.println("start method after test ...");
        System.out.println("start method after test ...");
        System.out.println("start method after test ...");
        System.out.println("start method after test ...");
    }
}
