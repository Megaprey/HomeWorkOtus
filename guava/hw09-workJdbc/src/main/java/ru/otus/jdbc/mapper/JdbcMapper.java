package ru.otus.jdbc.mapper;

import java.sql.SQLException;

public interface JdbcMapper<T> {
    void insert(T objectData) throws SQLException, IllegalAccessException;

    void update(T objectData) throws IllegalAccessException;

    void insertOrUpdate(T objectData) throws IllegalAccessException;

    T findById(long id, Class<T> clazz);
}