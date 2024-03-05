package org.example.daoImpl;

import org.example.dao.AbstractDAO;
import org.example.models.Apartment;

import java.sql.Connection;

public class ApartmentsDAOImpl extends AbstractDAO<Apartment> {
    public ApartmentsDAOImpl(Connection conn, String table) {
        super(conn, table);
    }
}
