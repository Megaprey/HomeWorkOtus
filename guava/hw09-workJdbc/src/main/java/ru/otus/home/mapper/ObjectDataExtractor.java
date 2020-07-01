package ru.otus.home.mapper;

import ru.otus.jdbc.mapper.EntityClassMetaData;
import ru.otus.jdbc.mapper.Id;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectDataExtractor<T> implements EntityClassMetaData<T> {
    private Class<?> clazz;
    private Field[] fields;

    public ObjectDataExtractor(T obj) {
        clazz = obj.getClass();
        fields = clazz.getDeclaredFields();
    }
    public ObjectDataExtractor(Class<T> clazz) {
        this.clazz = clazz;
        fields = clazz.getDeclaredFields();
    }

    @Override
    public String getName() {
        return clazz.getName();
    }

    @Override
    public Constructor<T> getConstructor() throws NoSuchMethodException {
        return (Constructor<T>) clazz.getConstructor();
    }

    @Override
    public Field getIdField() {
        Field fieldWithIdAnnotation = null;
        for (Field field:fields) {
            if (field.isAnnotationPresent(Id.class)) {
                fieldWithIdAnnotation = field;
            }
        }
        return fieldWithIdAnnotation;
    }

    @Override
    public List<Field> getAllFields() {
        return Arrays.asList(fields);
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        List<Field> allFields = getAllFields();
        List<Field> fieldsWithoutId = new ArrayList<>();
        for (Field field:allFields) {
            if (!field.equals(getIdField())) {
                fieldsWithoutId.add(field);
            }
        }

        return fieldsWithoutId;
    }
}
