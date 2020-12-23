package com.oshovskii.hibernate.spring.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        GlobalSessionFactory sessionFactory = context.getBean("globalSessionFactory", GlobalSessionFactory.class);
        ProductDao productDao = context.getBean("productDao", ProductDao.class);
        UserDao userDao = context.getBean("userDao", UserDao.class);

        try {
            userDao.findAllProductsByUserId(2L);
            System.out.println("-------------------------------------------------------------");
            productDao.findAllUsersByProductId(2L);
            System.out.println("-------------------------------------------------------------");
            productDao.findAll();
            System.out.println("-------------------------------------------------------------");
            productDao.deleteById(1L);
            System.out.println("-------------------------------------------------------------");
            productDao.saveOrUpdate(new Product( "super cat", 999999));
            System.out.println("-------------------------------------------------------------");
            productDao.findById(3L);
            System.out.println("-------------------------------------------------------------");
            userDao.sumUserSpend(1L);

        } finally {
            sessionFactory.shutdown();
            context.close();
        }
    }
}

