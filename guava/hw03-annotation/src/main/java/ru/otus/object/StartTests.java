package ru.otus.object;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class StartTests {
    public void start(String className){
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Method[] methodsPublic = clazz.getMethods();
        ArrayList<Method> afterMethods = new ArrayList<>();
        ArrayList<Method> beforeMethods = new ArrayList<>();
        ArrayList<Method> testMethods = new ArrayList<>();

        System.out.println("--- public methods:");
        for (Method method : methodsPublic) {
            Annotation[] annotations = method.getDeclaredAnnotations();
            addMethodWithAnnotation(afterMethods, method, "ru.otus.annotation.After", annotations);
            addMethodWithAnnotation(beforeMethods, method, "ru.otus.annotation.Before", annotations);
            addMethodWithAnnotation(testMethods, method, "ru.otus.annotation.Test", annotations);
        }
        try {
            runMethods(beforeMethods, clazz);
            runMethods(testMethods, clazz);
            runMethods(afterMethods, clazz);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void addMethodWithAnnotation(ArrayList<Method> methodList, Method method, String annotationName, Annotation[] methAnnotations){
        for (Annotation annotation : methAnnotations) {
            if(annotation.annotationType().getName().equals(annotationName)){
                methodList.add(method);
            }
        }
    }

    private void runMethods(ArrayList<Method> methods, Class clazz) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object object = clazz.getConstructor().newInstance();
        for (Method method: methods) {
            method.invoke(object);
        }
    }

    public static void main(String[] args) {
        new StartTests().start(ThreeMethods.class.getCanonicalName());
    }

}
