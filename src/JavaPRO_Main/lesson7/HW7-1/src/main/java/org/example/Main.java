package org.example;
/*
На JDBC, можна на основі AbstractDAO:

1) Спроектувати базу "Квартири". Кожен запис
у базі містить дані про квартиру (район,
адреса, площа, кільк. кімнат, ціна). Зробити
можливість вибірки квартир зі списку за
параметрів.
2) Створити проект "База даних замовлень". Створити
таблиці «Товари», «Клієнти» та «Замовлення».
Написати код для додавання нових клієнтів,
товарів та оформлення замовлень.
*/

import org.example.daoImpl.ApartmentsDAOImpl;
import org.example.daoImpl.ClientsDAOImpl;
import org.example.daoImpl.OrderDAOImpl;
import org.example.daoImpl.ThingsDAOImpl;
import org.example.models.Apartment;
import org.example.models.Client;
import org.example.models.Order;
import org.example.models.Thing;
import org.example.utils.ConnectionFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        //1
        try (Connection conn = ConnectionFactory.getConnection()) {

            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Apartments");
//                    st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            ApartmentsDAOImpl dao = new ApartmentsDAOImpl(conn, "Apartments");
            dao.createTable(Apartment.class);
            Apartment apartment1 = new Apartment("qwe1", "qwe1", 1, 1, 1);
            Apartment apartment2 = new Apartment("qwe1", "qwe1", 1, 2, 2);
            Apartment apartment3 = new Apartment("qwe3", "qwe3", 3, 3, 3);
            Apartment apartment4 = new Apartment("qwe4", "qwe4", 4, 4, 4);

            dao.add(apartment1);
            dao.add(apartment2);
            dao.add(apartment3);
            dao.add(apartment4);

            List<Apartment> list = dao.getAll(Apartment.class, new String[]{"district", "qwe1", "addresses", "qwe1", "area", "1"});
            for (Apartment apartment : list)
                System.out.println(apartment);
        }


        //2
        try (Connection conn = ConnectionFactory.getConnection()) {
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Things");
                    st.execute("DROP TABLE IF EXISTS Clients");
                    st.execute("DROP TABLE IF EXISTS Orders");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            ThingsDAOImpl thingsDAO = new ThingsDAOImpl(conn, "Things");
            ClientsDAOImpl clientsDAO = new ClientsDAOImpl(conn, "Clients");
            OrderDAOImpl orderDAO = new OrderDAOImpl(conn, "Orders");

            thingsDAO.createTable(Thing.class);
            clientsDAO.createTable(Client.class);
            orderDAO.createTable(Order.class);

            thingsDAO.add(new Thing("potato", "blessed"));
            clientsDAO.add(new Client("Patrick", "Saint", 2024 - 387));
            orderDAO.add(new Order("Best eating", "Saint Patrick"));


            List<Client> list = clientsDAO.getAll(Client.class, new String[]{"secondName", "Saint"});
            for (Client cli : list)
                System.out.println(cli);
        }
    }
}
