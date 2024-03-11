package org.example.daoImpl;

import org.example.dao.AbstractDAO;
import org.example.models.Thing;

import java.sql.Connection;

public class ThingsDAOImpl extends AbstractDAO<Thing> {
    public ThingsDAOImpl(Connection conn, String table) {
        super(conn, table);
    }
}
