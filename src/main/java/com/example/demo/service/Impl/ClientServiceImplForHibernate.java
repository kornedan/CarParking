package com.example.demo.service.Impl;

import com.example.demo.model.Client;
import com.example.demo.service.IClientService;
import com.example.demo.service.IHibernateSessionFactoryService;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClientServiceImplForHibernate implements IClientService {

    @Autowired
    private IHibernateSessionFactoryService iHibernateSessionFactoryService;

        public void addToDB(Client client) {
            Session session = this.iHibernateSessionFactoryService.getSession();
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(client);
            transaction.commit();
            session.close();
        }

        public Client dataOfClient(String login) {
            Session session = this.iHibernateSessionFactoryService.getSession();
            Transaction tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(Client.class);
            criteria.add(Restrictions.eq("login", login));

            Client client = (Client) criteria.uniqueResult();
            tx.commit();
            session.close();
            return client;
        }
    }



