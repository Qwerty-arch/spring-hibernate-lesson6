package com.oshovskii.hibernate.spring.context;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    private static GlobalSessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(GlobalSessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void findAllProductsByUserId( Long id ) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            System.out.println(user);
            System.out.println("Products: ");
            for (Product p : user.getProducts()) {
                System.out.println(p.getTitle());
            }
            session.getTransaction().commit();
        }
    }

    public void sumUserSpend(Long id) {
        try (Session session = sessionFactory.getFactory().getCurrentSession()) {
            session.beginTransaction();
            User user = session.get(User.class, id);
            System.out.println(user);
            System.out.println("Products: ");
            int sum = 0;
            for (Product p : user.getProducts()) {
                sum = p.getPrice() + sum;
            }
            System.out.println("   User final sum: " + sum);
            System.out.println("User buy: ");
            for (Product p : user.getProducts()) {
                System.out.println("   " + p.getTitle());
            }
            session.getTransaction().commit();
        }
    }
}
