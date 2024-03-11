package org.homeWork;
/**
 * 2. * Створити базу даних «Банк» з таблицями «Користувачі»,
 * «Транзакції», «Рахунки» і «Курси валют». Рахунок буває 3-х видів:
 * USD, EUR, UAH. Написати запити для поповнення рахунку в потрібній валюті,
 * переводу коштів з одного рахунку на інший, конвертації валюти по курсу в рамках рахунків одного користувача.
 * Написати запит на отримання сумарних коштів на рахунках одного користувача в UAH (по курсу).
 */

import org.homeWork.models.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


public class HW82 {

    static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bank");
    static EntityManager entityManager = entityManagerFactory.createEntityManager();

    public static void main(String[] args) {
        try {
            try {
                addSomeExchangeRates(new ExchangeRates("UAHtoUSD", 0.026), new ExchangeRates("USDtoUAH", 38.0836),
                        new ExchangeRates("EURtoUAH", 41.4883), new ExchangeRates("UAHtoEUR", 0.024),
                        new ExchangeRates("EURtoUSD", 1.09), new ExchangeRates("USDtoEUR", 0.91));
                List<ExchangeRates> erList = getListExchangeRates();

                Account account1 = new Account();
                Account account2 = new Account();
                User user1 = new User("Halk");
                User user2 = new User("Tor");

                user1.setAccount(account1);
                user2.setAccount(account2);
                account1.setUser(user1);
                account2.setUser(user2);

                addSomeUsers(user1, user2);

                topUpOrWithdraw(user1, 250.0, "USD", true);
                topUpOrWithdraw(user1, 20.0, "UAH", true);
                topUpOrWithdraw(user2, 550.0, "EUR", true);

                System.out.println(user1);
                System.out.println(user2);

                currencyConversion(user1, 25.0, "USD", "UAH", erList);
                currencyConversion(user2, 25.0, "EUR", "UAH", erList);

                System.out.println(user1);
                System.out.println(user2);
                transferMoneyTo(35.0, "USD", user1, user2);

                transferMoneyTo(35.0, "EUR", user2, user1);


                System.out.println(user1);
                System.out.println(user2);
                System.out.println(getAllMoneyInUAH(user1, erList));
                System.out.println(getAllMoneyInUAH(user2, erList));

                System.out.println(getAllTransaction());
            } finally {
                entityManagerFactory.close();
                entityManager.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Transaction getAllMoneyInUAH(User user, List<ExchangeRates> exchangeRatesList) {
        Transaction transaction = new Transaction();
        entityManager.getTransaction().begin();
        transaction.getAllMoneyInUAH(user, exchangeRatesList);
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
        return transaction;
    }

    public static void transferMoneyTo(Double howMuch, String currency, User from, User to) {
        Transaction transaction = new Transaction();
        entityManager.getTransaction().begin();
        transaction.transferMoneyTo(howMuch, currency, from, to);
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
    }

    public static void currencyConversion(User user, Double howMuch, String fromCurrency, String toCurrency, List<ExchangeRates> exchangeRatesList) {
        Transaction transaction = new Transaction();
        entityManager.getTransaction().begin();
        transaction.currencyConversion(user, howMuch, fromCurrency, toCurrency, exchangeRatesList);
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
    }

    public static void topUpOrWithdraw(User user, Double howMuch, String currency, boolean trueAddFalseMinus) {
        Transaction transaction = new Transaction();
        entityManager.getTransaction().begin();
        transaction.topUpOrWithdraw(user, howMuch, currency, trueAddFalseMinus);
        entityManager.persist(transaction);
        entityManager.getTransaction().commit();
    }

    public static List<Transaction> getAllTransaction() {
        Query query = entityManager.createQuery("SELECT t FROM Transaction t");
        return query.getResultList();
    }

    public static void addSomeUsers(User... user) {
        entityManager.getTransaction().begin();
        for (User u : user) {
            entityManager.persist(u.getAccount());
            entityManager.persist(u);
        }
        entityManager.getTransaction().commit();
    }

    public static List<ExchangeRates> getListExchangeRates() {
        Query query = entityManager.createQuery("SELECT l FROM ExchangeRates l");
        return query.getResultList();
    }

    public static void addSomeExchangeRates(ExchangeRates... exchangeRates) {
        entityManager.getTransaction().begin();

        for (ExchangeRates er : exchangeRates) {
            entityManager.persist(er);
        }
        entityManager.getTransaction().commit();
    }

}
