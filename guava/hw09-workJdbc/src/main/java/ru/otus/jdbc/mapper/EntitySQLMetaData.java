package ru.otus.jdbc.mapper;

public interface EntitySQLMetaData {
    String getSelectAllSql();

    String getSelectByIdSql() throws IllegalAccessException;

    String getInsertSql() throws IllegalAccessException;

    String getUpdateSql() throws IllegalAccessException;
}
