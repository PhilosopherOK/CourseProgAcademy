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
import com.mysql.cj.xdevapi.Client;
import org.example.daoImpl.ApartmentsDAOImpl;
import org.example.models.Apartment;
import org.example.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {
            // remove this
            try {
                try (Statement st = conn.createStatement()) {
                    st.execute("DROP TABLE IF EXISTS Clients");
//                    st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL, age INT)");
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            ApartmentsDAOImpl dao = new ApartmentsDAOImpl(conn, "Apartments");
            dao.createTable(Apartment.class);


            dao.add(c1);
            dao.add(c2);
            dao.add(c3);
            dao.add(c4);

            int id = c1.getId();
            System.out.println(id);
            int id2 = c2.getId();
            System.out.println(id2);

            List<Client> list = dao.getAll(Client.class, "test2", 2);
            for (Client cli : list)
                System.out.println(cli);

//            list.get(0).setAge(55);
//            dao.update(list.get(0));

            /*
            задача 1
            при добавлении обьекта в базу добавлять клиенту инкременитруишейся айдишник
            который база сама поставит после екзекюта и венуть его(айди)

            задача 2
            List<Client> list = dao.getAll(Client.class, "name", "age");
            взять с базы только тех клиентов имя и возраст которых совпадает с теми что передавали в параметре

            List<Client> list = dao.getAll(Client.class, "age");
            for (Client cli : list)
                System.out.println(cli);

             */

//            dao.delete(list.get(0));
        }
    }
}
