package org.example.daoImpl;

import org.example.dao.AbstractDAO;
import org.example.models.Client;

import java.sql.Connection;

public class ClientsDAOImpl extends AbstractDAO<Client> {
    public ClientsDAOImpl(Connection conn, String table) {
        super(conn, table);
    }
}
