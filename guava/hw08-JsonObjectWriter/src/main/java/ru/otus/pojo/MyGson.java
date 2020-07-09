package ru.otus.pojo;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class MyGson {
    private final static String apostrophe = "\"";
    public String toJson(Object obj) throws IllegalAccessException {
        String result = "{";
        if (obj == null){
            return "null";
        }
        Class<?> clazz = obj.getClass();
        if(clazz.isAssignableFrom(Byte.class) || clazz.isAssignableFrom(Short.class) ||
            clazz.isAssignableFrom(Integer.class) || clazz.isAssignableFrom(Long.class) ||
                clazz.isAssignableFrom(Double.class) || clazz.isAssignableFrom(Float.class)) {
            return "" + obj;
        }
        if(clazz.isAssignableFrom(String.class) || clazz.isAssignableFrom(Character.class)) {
            return apostrophe + obj + apostrophe;
        }
//        if(clazz.isArray()) {
//            int length = Array.getLength(obj);
//            result = "[";
//            for (int i = 0; i < length; i++) {
//                if (i != 0){
//                    result += ", ";
//                }
//                result += Array.get(obj, i);
//            }
//            result += "]";
//            return result;
//
//        }
        if (clazz.isArray()) {
            return arrayToJson(obj);
        }

        if (Collection.class.isAssignableFrom(clazz)) {
            return collectionToJson(obj);
        }

        if (Map.class.isAssignableFrom(clazz)) {
            return mapToJson(obj);
        }

        Field[] fieldsPublic = clazz.getDeclaredFields();
        int countField = 0;
        for (Field field:fieldsPublic) {
            result += apostrophe + field.getName() + apostrophe + ":" + toJson(field.get(obj));

            countField++;
            if (countField != fieldsPublic.length) {
                result += ",";
            }

        }
        result += "}";
        return result;
    }

    private String arrayToJson(Object obj){
        Object mass = obj;
        Class<?> clazz = obj.getClass();
        String arrayString = Arrays.deepToString(new Object[]{mass}).replace(" ", "").
                replace("[[", "[").replace("]]", "]");
        if(clazz.componentType().isAssignableFrom(String.class)) {
            arrayString = arrayString.replace("[", "[" + apostrophe).
                    replace(",", apostrophe + "," + apostrophe).
                    replace("]", apostrophe+ "]");
        }
//            System.out.println("arrayString ====== " + arrayString);
        return arrayString;
    }

    private String collectionToJson(Object obj){
        String result = "[";
        Collection<?> mass = (Collection<?>) obj;
        int count = 0;
        for (var element : mass) {
            if (element instanceof String){
                result += apostrophe + element + apostrophe;
            } else {
                result += element;
            }
            count++;
            if (count != mass.size()) {
                result += ",";
            }
        }
        result += "]";
        return result;
    }

    private String mapToJson(Object obj){
        String result = "{";
        Map<?, ?> map = (Map<?, ?>) obj;
        int count = 0;
        for (var element : map.entrySet()) {
            result += apostrophe + element.getKey() + apostrophe + ":" + apostrophe
                    + element.getValue() + apostrophe;
            count++;
            if (count != map.size()) {
                result += ",";
            }
        }
        result += "}";
        return result;
    }
}