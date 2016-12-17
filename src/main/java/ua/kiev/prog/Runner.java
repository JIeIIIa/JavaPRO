package ua.kiev.prog;/*
Создать таблицу «Меню в ресторане». Колонки: название блюда, его стоимость, вес, наличие скидки. Написать код для
добавления записей в таблицу и их выборки по критериям «стоимость от-до», «только со скидкой», выбрать набор блюд
так, чтобы их суммарный вес был не более 1 КГ.
*/

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class Runner {
    static private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RestaurantMenu");
        EntityManager em = emf.createEntityManager();
        try {
            String c = "";

            while (!"0".equals(c)) {
                System.out.println();
                System.out.println("1 - Show all dishes");
                System.out.println("2 - Generate dishes");
                System.out.println("3 - Create dish");
                System.out.println("4 - Choose by cost");
                System.out.println("5 - Only with discount");
                System.out.println("6 - Set with a total weight less than 1 kg ");
                System.out.println();
                System.out.println("0 - Exit");
                c = scanner.nextLine();
                switch (c) {
                    case "1":
                        showMenu(em);
                        break;
                    case "2":
                        System.out.print("\tCount dishes for generate = ");
                        int cnt = scanner.nextInt();
                        scanner.nextLine();
                        generate(em, cnt);
                        break;
                    case "3":
                        add(em);
                        break;
                    case "4":
                        byCost(em);
                        break;
                    case "5":
                        onlyWithDiscount(em);
                        break;
                    case "6":
                        chooseDishesByWeight(em, 1.0);
                        break;
                }
            }
        } finally {
            em.close();
            emf.close();
        }
    }



    public static void generate(EntityManager em, int count) {
        em.getTransaction().begin();
        try {
            for (int i = 0; i < count; i++) {
                Random rnd = new Random();
                Menu menu = new Menu("Dish_" + i,
                        rnd.nextDouble() + 0.01,
                        rnd.nextInt(100000) + 1,
                        rnd.nextInt(100) % 2 == 0 ? rnd.nextInt(20) : 0);
                em.persist(menu);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void add(EntityManager em) {
        System.out.println("New dish:");

        try {
            System.out.print("\ttitle = ");
            String title = scanner.nextLine();
            System.out.print("\tweight = ");
            double weight = Double.parseDouble(scanner.nextLine());
            System.out.print("\tcost = ");
            int cost = (int) (Double.parseDouble(scanner.nextLine()) * 100);
            System.out.print("\tdiscount = ");
            int discount = Integer.parseInt(scanner.nextLine());

            em.getTransaction().begin();
            Menu menu = new Menu(title, weight, cost, discount);
            em.persist(menu);
            em.getTransaction().commit();
        } catch (NumberFormatException ex) {
            System.out.println("Error!");
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    public static void byCost(EntityManager em) {
        System.out.print("\tmin cost = ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("\tmax cost = ");
        double max = Double.parseDouble(scanner.nextLine());
        Query query = em.createNamedQuery("byCost", Menu.class);
        query.setParameter("minCost", (int)(min*100));
        query.setParameter("maxCost", (int)(max*100));
        List<Menu> result = query.getResultList();
        print(result);
    }

    public static void onlyWithDiscount(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
        Root<Menu> from = query.from(Menu.class);
        query.select(from).where(cb.gt(from.<Integer>get("discount"), 0));
        TypedQuery<Menu> result = em.createQuery(query);
        List<Menu> list = result.getResultList();

        print(list);
    }

    public static void showMenu(EntityManager em) {
        try {
            System.out.println("MENU:");
            Query query = em.createNamedQuery("showAll", Menu.class);
            List<Menu> result = query.getResultList();
            print(result);
        } catch (Exception e) { /*NOP*/}
    }

    private static void print(List<Menu> list) {
        for (Menu menu : list) {
            System.out.println(menu);
        }
    }

    public static void chooseDishesByWeight(EntityManager em, double totalWeight) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Menu> cq = cb.createQuery(Menu.class);
        Root<Menu> from = cq.from(Menu.class);
        cq.select(from)
                .where(cb.lt(from.<Double>get("weight"), totalWeight))
                .orderBy(cb.asc(from.<Double>get("weight")));
        List<Menu> result = em.createQuery(cq).getResultList();

        double currentWeight = 0.0;
        List<Menu> choose = new ArrayList<>();

        for (Menu menu : result) {
            if (menu.getWeight() < totalWeight - currentWeight) {
                currentWeight += menu.getWeight();
                choose.add(menu);
            }
        }

        System.out.println("You may choose this dishes");
        print(choose);
        System.out.println(String.format("Total weight = %.3f", currentWeight));
    }
}


