package org.example.dao;

import org.example.utils.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class AbstractDAO<T> {
    private final Connection conn;
    private final String table;

    public AbstractDAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    public void createTable(Class<T> cls) {
        Field[] fields = cls.getDeclaredFields();
        Field id = getPrimaryKeyField(null, fields);

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ")
                .append(table)
                .append("(");

        sql.append(id.getName())
                .append(" ")
                .append(" INT AUTO_INCREMENT PRIMARY KEY,");

        for (Field f : fields) {
            if (f != id) {
                f.setAccessible(true);

                sql.append(f.getName()).append(" ");

                if (f.getType() == Integer.class) {
                    sql.append("INT,");
                } else if (f.getType() == String.class) {
                    sql.append("VARCHAR(100),");
                } else
                    throw new RuntimeException("Wrong type");
            }
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");

        try {
            try (Statement st = conn.createStatement()) {
                st.execute(sql.toString());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void add(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = getPrimaryKeyField(t, fields);
            StringBuilder names = new StringBuilder();
            StringBuilder values = new StringBuilder();
            // insert into t (name,age) values("..",..
            for (Field f : fields) {
                if (f != id) {
                    f.setAccessible(true);

                    names.append(f.getName()).append(',');
                    values.append('"').append(f.get(t)).append("\",");
                }
            }
            names.deleteCharAt(names.length() - 1); // last ','
            values.deleteCharAt(values.length() - 1);

            String sql = "INSERT INTO " + table + "(" + names.toString() +
                    ") VALUES(" + values.toString() + ")";
            conn.setAutoCommit(false);
            try (Statement st = conn.createStatement()) {
                st.execute(sql);
                ResultSet resultSet = st.executeQuery("SELECT LAST_INSERT_ID();");
                if (resultSet.next()) {
                    Arrays.stream(fields).filter(f -> f == id).peek(f -> f.setAccessible(true)).forEach(f -> {
                        try {
                            f.set(t, (int) resultSet.getLong(1));
                        } catch (IllegalAccessException | SQLException e) {
                            e.printStackTrace();
                        }
                    });
                }
                conn.commit();
            }
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public void update(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = getPrimaryKeyField(t, fields);

            StringBuilder sb = new StringBuilder();

            for (Field f : fields) {
                if (f != id) {
                    f.setAccessible(true);

                    sb.append(f.getName())
                            .append(" = ")
                            .append('"')
                            .append(f.get(t))
                            .append('"')
                            .append(',');
                }
            }

            sb.deleteCharAt(sb.length() - 1);

            // update t set name = "aaaa", age = "22" where id = 5
            String sql = "UPDATE " + table + " SET " + sb.toString() + " WHERE " +
                    id.getName() + " = \"" + id.get(t) + "\"";

            try (Statement st = conn.createStatement()) {
                st.execute(sql);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = getPrimaryKeyField(t, fields);

            String sql = "DELETE FROM " + table + " WHERE " + id.getName() +
                    " = \"" + id.get(t) + "\"";

            try (Statement st = conn.createStatement()) {
                st.execute(sql);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<T> getAll(Class<T> cls) {
        List<T> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM " + table)) {
                    ResultSetMetaData md = rs.getMetaData();

                    while (rs.next()) {
                        T t = cls.getDeclaredConstructor().newInstance(); //!!!
                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);
                            Field field = cls.getDeclaredField(columnName);
                            field.setAccessible(true);

                            field.set(t, rs.getObject(columnName));
                        }
                        res.add(t);
                    }
                }
            }
            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public List<T> getAll(Class<T> cls, String[] oddFileNameEvenFileValue) {
        if (oddFileNameEvenFileValue.length % 2 != 0) return null;

        String sqlQuery = createAQueryFromData(cls, oddFileNameEvenFileValue);

            List<T> res = new ArrayList<>();
            try {
                try (Statement st = conn.createStatement()) {
                    try (ResultSet rs = st.executeQuery(sqlQuery)) {
                        ResultSetMetaData md = rs.getMetaData();

                        while (rs.next()) {
                            T t = cls.getDeclaredConstructor().newInstance(); //!!!
                            for (int i = 1; i <= md.getColumnCount(); i++) {
                                String columnName = md.getColumnName(i);
                                Field field = cls.getDeclaredField(columnName);
                                field.setAccessible(true);

                                field.set(t, rs.getObject(columnName));
                            }
                            res.add(t);
                        }
                    }
                }
                return res;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

    private String createAQueryFromData(Class<T> cls, String[] oddFileNameEvenFileValue){
        StringBuilder sqlQuery = new StringBuilder("SELECT * FROM " + table + " WHERE ");
        T clazz = null;
        try {
            clazz = (T) cls.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Field[] fields = clazz.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < oddFileNameEvenFileValue.length; j += 2) {
                if (fields[i].getName().equals(oddFileNameEvenFileValue[j])) {
                    sqlQuery.append(oddFileNameEvenFileValue[j] + "=");
                    if (fields[i].getType() == String.class)
                        sqlQuery.append("'");
                    sqlQuery.append(oddFileNameEvenFileValue[j + 1]);
                    if (fields[i].getType() == String.class)
                        sqlQuery.append("'");
                    sqlQuery.append(" AND ");
                }
            }
        }
        sqlQuery = new StringBuilder(sqlQuery.substring(0, sqlQuery.length() - 5));
        sqlQuery.append(";");
        return sqlQuery.toString();
    }

    private Field getPrimaryKeyField(T t, Field[] fields) {
        Field result = null;

        for (Field f : fields) {
            if (f.isAnnotationPresent(Id.class)) {
                result = f;
                result.setAccessible(true);
                break;
            }
        }

        if (result == null)
            throw new RuntimeException("No Id field found");

        return result;
    }
}
