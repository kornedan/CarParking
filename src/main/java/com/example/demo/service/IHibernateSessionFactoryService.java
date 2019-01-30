package com.example.demo.service;

import org.hibernate.Session;

public interface IHibernateSessionFactoryService {
    Session getSession();
}
