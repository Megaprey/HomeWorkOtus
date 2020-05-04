package ru.otus.object;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class StartTests {
    public void start(String className){
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        Method[] methodsPublic = clazz.getMethods();
        ArrayList<Method> afterMethods = new ArrayList<>();
        ArrayList<Method> beforeMethods = new ArrayList<>();
        ArrayList<Method> testMethods = new ArrayList<>();

        System.out.println("--- public methods:");
        for (Method method : methodsPublic) {
            addMethodWithAnnotation(afterMethods, method, After.class);
            addMethodWithAnnotation(beforeMethods, method, Before.class);
            addMethodWithAnnotation(testMethods, method, Test.class);
        }
        try {
            Object object = clazz.getConstructor().newInstance();
            runMethods(beforeMethods, object);
            runMethods(testMethods, object);
            runMethods(afterMethods, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMethodWithAnnotation(ArrayList<Method> methodList, Method method, Class annotationClass){
        if (method.isAnnotationPresent(annotationClass)) {
            methodList.add(method);
        }
    }

    private void runMethods(ArrayList<Method> methods, Object object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        for (Method method: methods) {
            method.invoke(object);
        }
    }

    public static void main(String[] args) {
        new StartTests().start(ThreeMethods.class.getCanonicalName());
    }

}
