package com.example.demo.service;

import com.example.demo.model.Client;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Component
public class ClientServiceImpl implements IClientService {

    public void addToDB(Client client) {
        try {
            String SQL = "INSERT INTO data (login, password, registrationNumber, parkingSpace) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = DBHandler.connection.prepareStatement(SQL);
            String hash = DigestUtils.md5Hex(client.getPassword());
            preparedStatement.setString(1, client.getLogin());
            preparedStatement.setString(2, hash);
            preparedStatement.setString(3, client.getRegistrationNumber());
            preparedStatement.setInt(4, client.getParkingSpace());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Client dataOfClient(String login) {
        try {
            Client client = new Client();
            String SQL = "SELECT * FROM data WHERE login= ?";
            PreparedStatement preparedStatement = DBHandler.connection.prepareStatement(SQL);
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            client.setLogin(rs.getString("login"));
            client.setRegistrationNumber(rs.getString("registrationNumber"));
            client.setParkingSpace(rs.getInt("parkingSpace"));
            return client;
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}

