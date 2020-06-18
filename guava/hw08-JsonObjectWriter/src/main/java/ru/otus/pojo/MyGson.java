package ru.otus.pojo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class MyGson {
    public String toJson(Object obj) throws IllegalAccessException {
        String result = "{";
        Class clazz = obj.getClass();
        String apostrophe = "\"";
        Field[] fieldsPublic = clazz.getDeclaredFields();
        int countField = 0;
        for (Field field:fieldsPublic) {
            Class<?> fld = field.getType();
            Class<?> fieldType = field.getType();
            if (Map.class.isAssignableFrom(fieldType)) {
                result += apostrophe + field.getName() + apostrophe + ":{";
                Map<?, ?> map = (Map<?, ?>) field.get(obj);
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
            }
            if (Collection.class.isAssignableFrom(fieldType)) {

                result += apostrophe + field.getName() + apostrophe + ":[";
                Collection<?> mass = (Collection<?>) field.get(obj);
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
            }
            if (fieldType.isArray()) {
                Object mass = field.get(obj);
                String arrayString = Arrays.deepToString(new Object[]{mass}).replace(" ", "").
                        replace("[[", "[").replace("]]", "]");
                if(fieldType.componentType().isAssignableFrom(String.class)) {
                    arrayString = arrayString.replace("[", "[" + apostrophe).
                            replace(",", apostrophe + "," + apostrophe).
                            replace("]", apostrophe+ "]");
                }
                result += apostrophe + field.getName() + apostrophe + ":" + arrayString;
            }
            if (fieldType.isAssignableFrom(String.class) || fieldType.isAssignableFrom(boolean.class)){
                result += apostrophe + field.getName() + apostrophe + ":" + apostrophe + field.get(obj) + apostrophe;
            } else {
                if (fieldType.isPrimitive()) {
                        result += apostrophe + field.getName() + apostrophe + ":" + field.get(obj);
                }
            }

            countField++;
            if (countField != fieldsPublic.length) {
                result += ",";
            }

        }
        System.out.println("---------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------");
        result += "}";
        System.out.println(result);
        return result;
    }
}
