package org.example;

import org.example.model.Product;
import org.example.provider.SessionFactoryProvider;
import org.example.utils.Utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("0. Iesire");
            System.out.println("1. Vizualizare produse");
            System.out.println("2. Adaugare produs");
            System.out.println("3. Stergere produs");
            System.out.println("4. Editare produs");
            int number = scanner.nextInt();
            if (number == 0)
                break;
            else
                switch (number) {
                    case 1: {
                        Utils.printProducts(viewProducts());
                        break;
                    }
                    case 2: {
                        addProduct(scanner);
                        break;
                    }
                    case 3: {
                        deletePoduct(scanner);
                        break;
                    }
                    case 4: {
                        editProduct(scanner);
                        break;
                    }
                    default:
                        System.out.println("Nu exista optiunea. Va rugam reincercati.");
                        break;
                }
        }

    }

    private static void addProduct(Scanner scanner) {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction t = session.beginTransaction();

        Product p = new Product();
        System.out.print("Name : ");
        p.setName(scanner.next());
        System.out.print("Price : ");
        p.setPrice(scanner.nextFloat());
        System.out.print("Stock : ");
        p.setStock(scanner.nextInt());
        //session.merge(p);
        session.persist(p);
        t.commit();

        sessionFactory.close();
    }

    private static List<Product> viewProducts() {
        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();
//       Query<Product> query = session.createNativeQuery("SELECT * FROM product",Product.class);
        Query<Product> query = session.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    private static void deletePoduct(Scanner scanner){
        Product product = Utils.printProductsWithIndexes(viewProducts(),scanner);

        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction t = session.beginTransaction();

        session.remove(product);
        t.commit();

    }
    private static void editProduct(Scanner scanner){
        Product product = Utils.printProductsWithIndexes(viewProducts(),scanner);

        SessionFactory sessionFactory = SessionFactoryProvider.provideSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction t = session.beginTransaction();


        System.out.print("Name : ");
        product.setName(scanner.next());
        System.out.print("Price : ");
        product.setPrice(scanner.nextFloat());
        System.out.print("Stock : ");
        product.setStock(scanner.nextInt());
        session.merge(product);
//        session.persist(product);
        t.commit();

    }
}
