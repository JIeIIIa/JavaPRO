/*
Создать таблицу «Меню в ресторане». Колонки: название блюда, его стоимость, вес, наличие скидки. Написать код для
добавления записей в таблицу и их выборки по критериям «стоимость от-до», «только со скидкой», выбрать набор блюд
так, чтобы их суммарный вес был не более 1 КГ.
*/

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Runner {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestaurantMenu");
        EntityManager em = emf.createEntityManager();
        try {

        } finally {
            em.close();
            emf.close();
        }

    }
}
