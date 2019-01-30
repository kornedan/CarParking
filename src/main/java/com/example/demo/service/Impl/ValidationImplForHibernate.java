package com.example.demo.service.Impl;

import com.example.demo.model.Client;
import com.example.demo.service.IHibernateSessionFactoryService;
import com.example.demo.service.IValidation;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationImplForHibernate implements IValidation {

    @Autowired
    private IHibernateSessionFactoryService iHibernateSessionFactoryService;

    public boolean validationToRegistration(String login) {
        Session session = this.iHibernateSessionFactoryService.getSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("login", login));

        Client clientCheck = (Client) criteria.uniqueResult();
        if(clientCheck == null)
            return false;
        tx.commit();
        session.close();

        return true;
    }

    public Integer validationParkingSpace() {
      /*  try {
            ArrayList<Integer> bookParkingSpace = new ArrayList();
            String SQL = "SELECT parkingSpace FROM data";
            PreparedStatement preparedStatement = DBHandler.connection.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                bookParkingSpace.add(rs.getInt("parkingSpace"));
            }
            return assignParkingSpace(bookParkingSpace);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
        */
        Session session = this.iHibernateSessionFactoryService.getSession(); //uruchomienie sesji

        Transaction tx = session.beginTransaction();
        List<Client> list = session.createCriteria(Client.class).list();
        tx.commit();
        session.close();
        return assignParkingSpace(list);

    }

    private int assignParkingSpace(List<Client> bookParkingSpace) {
        Integer clientParkingSpace = 1;
        if(bookParkingSpace != null) {
            for (Client parkingSpace : bookParkingSpace) {
                if (clientParkingSpace.equals(parkingSpace.getParkingSpace()))
                    ++clientParkingSpace;
            }
        }
        return clientParkingSpace;
    }

    public boolean validationLogging(String login, String password) {
        Session session = this.iHibernateSessionFactoryService.getSession();
        Transaction tx = session.beginTransaction();
        Criteria criteria = session.createCriteria(Client.class);
        criteria.add(Restrictions.eq("login", login));

        Client clientCheck = (Client) criteria.uniqueResult();
        if(clientCheck == null)
            return false;
        tx.commit();
        session.close();
        if (clientCheck.getPassword().equals(password))
        return true;
        else
            return false;

    }

}
