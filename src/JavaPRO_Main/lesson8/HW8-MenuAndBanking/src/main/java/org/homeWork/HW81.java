package org.homeWork;
/**
 * 1) Створити таблицю «Меню в ресторані». Колонки: назва страви,
 * його вартість, вага, наявність знижки. Написати код для
 * додавання записів до таблиці та їх вибору за критеріями
 * вартість від-до», «тільки зі знижкою, вибрати набір страв
 * так, щоб їх сумарна вага була не більше 1 КГ.
 */


import org.homeWork.models.PositionInMenu;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class HW81 {
    static EntityManagerFactory entityManagerFactory;
    static EntityManager entityManager;

    public static void main(String[] args) {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("HW8");
            entityManager = entityManagerFactory.createEntityManager();
            try {
                addMenu(new PositionInMenu("Big Tasty", 180, 400, false));
                addMenu(new PositionInMenu("Beef Cheddar", 250, 500, true));
                addMenu(new PositionInMenu("Big Mac", 120, 250, false));
                addMenu(new PositionInMenu("Salt", 150, 400, false));
                addMenu(new PositionInMenu("cheeseburger", 100, 150, false));
                addMenu(new PositionInMenu("Big Burger", 220, 550, true));
                addMenu(new PositionInMenu("Mac Rib", 190, 480, true));
                addMenu(new PositionInMenu("Pie", 50, 90, false));
                addMenu(new PositionInMenu("Sauce", 30, 30, false));

                System.out.println(getPositionWithSomePrise(40, 150));
                System.out.println(getPositionWithDiscount());
                System.out.println(getPositionsWithSummaryWeightLessWhenOneKilogram());

            } finally {
                entityManager.close();
                entityManagerFactory.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static PositionInMenu addMenu(PositionInMenu positionInMenu) {
        entityManager.getTransaction().begin();

        try {
            entityManager.persist(positionInMenu);

            entityManager.getTransaction().commit();

            System.out.println(positionInMenu.getId());
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        }
        return positionInMenu;
    }

    private static List<PositionInMenu> getPositionWithSomePrise(Integer from, Integer to) {
        Query query = entityManager.createQuery("SELECT m FROM PositionInMenu m WHERE price > " + from + " AND price < " + to, PositionInMenu.class);
        return (List<PositionInMenu>) query.getResultList();
    }

    private static List<PositionInMenu> getPositionWithDiscount() {
        Query query = entityManager.createQuery("SELECT m FROM PositionInMenu m WHERE discount=true" , PositionInMenu.class);
        return (List<PositionInMenu>) query.getResultList();
    }


    private static List<PositionInMenu> getPositionsWithSummaryWeightLessWhenOneKilogram() {
        Query query = entityManager.createQuery("SELECT m FROM PositionInMenu m" , PositionInMenu.class);
        List<PositionInMenu> allPositionInMenus = query.getResultList();
        List<PositionInMenu> resultMenu = new ArrayList<>();
        int summaryWeight = 0;


        for (int i = 0; i < allPositionInMenus.size(); i++) {
            PositionInMenu positionInMenu = allPositionInMenus.get(i);
            if((summaryWeight + positionInMenu.getWeight()) < 1000){
                summaryWeight += positionInMenu.getWeight();
                resultMenu.add(positionInMenu);
            }
        }
        return resultMenu;
    }
}





















