package org.homeWork;
/*
Домашка:

1) Знайти будь який сервіс, що повертає курс валют по HTTP REST API (Приват, Моно)

2) Написати додаток, що забере курс долара за останні 2-3 місяці кожен день і збереже записи в базу (JPA)

3) Написати код, що

а) Повуертатіме курс по даті
б) Повертатиме середній курс на проміжку часу
в) Повертатиме найбільний / найменший курс за період.
 */

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.homeWork.models.DailyExchangeRate;
import org.homeWork.models.JsonObj;

import javax.persistence.*;
import java.awt.dnd.DragSource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;


public class App {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void main(String[] args) {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("HW9");
            entityManager = entityManagerFactory.createEntityManager();
            getSomeDailyExchangeRateAndSave();

            Calendar calendar = Calendar.getInstance();
            calendar.set(2024, Calendar.JANUARY, 15);
            Date date1 = calendar.getTime();
            calendar.set(2024, Calendar.JANUARY, 25);
            Date date2 = calendar.getTime();

            System.out.println(getRatesFromDate(date1));
            System.out.println(getAverageRateFromDateToDate(date1, date2));
            System.out.println(getMaxRateFromDateToDate(date1, date2));
            System.out.println(getMinRateFromDateToDate(date1, date2));


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            entityManagerFactory.close();
            entityManager.close();
        }
    }



//    в) Повертатиме найбільний / найменший курс за період.
    public static Double getMinRateFromDateToDate(Date from, Date to) {
        List<Double> ratesList = getAllRateFromDateToDate(from, to);
        return ratesList.stream().min(Comparator.naturalOrder()).get();
    }
    public static Double getMaxRateFromDateToDate(Date from, Date to) {
        List<Double> ratesList = getAllRateFromDateToDate(from, to);
        return ratesList.stream().max(Comparator.naturalOrder()).get();
    }


    //    б) Повертатиме середній курс на проміжку часу
    public static List<Double> getAllRateFromDateToDate(Date from, Date to) {
        Query query = entityManager.createQuery(
                "SELECT d.rate FROM DailyExchangeRate d WHERE exchangedate> :dateFrom AND exchangedate < :dateTo");
        query.setParameter("dateFrom", from);
        query.setParameter("dateTo", to);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static Double getAverageRateFromDateToDate(Date from, Date to) {
        List<Double> ratesList = getAllRateFromDateToDate(from, to);
        Double sum = 0.0;
        for (int i = 0; i < ratesList.size(); i++) {
            sum += ratesList.get(i);
        }
        return sum / ratesList.size();
    }


    //    а) Повертатиме курс по даті
    public static Double getRatesFromDate(Date date) {
        Query query = entityManager.createQuery(
                "SELECT d.rate FROM DailyExchangeRate d WHERE exchangedate= :date");
        query.setParameter("date", date);
        try {
            return (Double) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public static void getSomeDailyExchangeRateAndSave() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
        List<JsonObj> jsonObjList = null;

        String url = "https://bank.gov.ua/NBU_Exchange/exchange_site?start=20240101&end=%2020240301&valcode=usd&sort=exchangedate&order=desc&json";
        URL obj = new URL(url.toString());
        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

        conn.setRequestMethod("GET");
        conn.setDoInput(true);

        try (InputStream is = conn.getInputStream()) {
            jsonObjList = objectMapper.readValue(conn.getInputStream(), new TypeReference<List<JsonObj>>() {
            });
        }
        try {
            entityManager.getTransaction().begin();
            for (JsonObj rate : jsonObjList) {
                DailyExchangeRate dailyExchangeRate = new DailyExchangeRate(rate.getExchangedate(), rate.getEnname(), rate.getRate());
                entityManager.persist(dailyExchangeRate);
                System.out.println(dailyExchangeRate);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
    }
}
