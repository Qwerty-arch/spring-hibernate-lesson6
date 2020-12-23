package com.oshovskii.hibernate.spring.context;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {

    private static GlobalSessionFactory sessionFactory;


    @Autowired
    public void setSessionFactory(GlobalSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void findAll() {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            System.out.println("==========\nFIND ALL PRODUCT\n==========");
            session.beginTransaction();
            List<Product> products = session.createQuery("from Product").getResultList();
            System.out.println(products + "\n");
            session.getTransaction().commit();
        }
    }

    public void saveOrUpdate(Product product) {
        if (product.getTitle() == null || product.getTitle().equals("")) {
            throw new IllegalArgumentException("product title bad"); //TODO написать свое исключение
        }
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            System.out.println("==========\nSAVE/UPDATE PRODUCT\n==========");
            session.beginTransaction();
            session.saveOrUpdate(product);
            session.getTransaction().commit();
            System.out.println(product);
        }
    }

    public void findById(Long id) {
        System.out.println("==========\nFIND PRODUCT BY ID " + id + "\n==========");
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product);
            session.getTransaction().commit();
        }
    }

    public void deleteById(Long id) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            System.out.println("==========\nDELETE PRODUCT BY ID " + id + "\n==========");
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product + " - delete");
            session.delete(product);
            session.getTransaction().commit();
        }
    }

    public void findAllUsersByProductId(Long id) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, id);
            System.out.println(product);
            System.out.println("Users: ");
            for (User u : product.getUsers()) {
                System.out.println(u.getName());
            }
            // user.getProducts().clear();
            session.getTransaction().commit();
        }
    }
}
