package org.example.daoImpl;

import org.example.dao.AbstractDAO;
import org.example.models.Order;

import java.sql.Connection;

public class OrderDAOImpl extends AbstractDAO<Order> {
    public OrderDAOImpl(Connection conn, String table) {
        super(conn, table);
    }
}
