package com.oshovskii.hibernate.spring.context;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope("prototype")
public class GlobalSessionFactory {

    private SessionFactory sessionFactory;

    @PostConstruct
    public void init() {
        PrepareDataApp.forcePrepareData();
        sessionFactory = new Configuration()
                .configure("configs/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public SessionFactory getFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void shutdown() {
        sessionFactory.close();
    }
}
