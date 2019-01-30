package com.example.demo.service.Impl;

import com.example.demo.service.IHibernateSessionFactoryService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateSessionFactoryServiceImpl implements IHibernateSessionFactoryService {


    private SessionFactory sessionFactory;

    public HibernateSessionFactoryServiceImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public Session getSession(){
        return this.sessionFactory.openSession();
    }
}
