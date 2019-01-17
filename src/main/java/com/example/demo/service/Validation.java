package com.example.demo.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class Validation implements ValidationInt {

    public boolean validationToRegistration(String login) {
        try {
            String SQL = "SELECT login FROM data WHERE login = ?";
            PreparedStatement preparedStatement = DBHandler.connection.prepareStatement(SQL);
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs == null) {
                return true;
            }
            while (rs.next()) {
                if (rs.getString("login").equals(login)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Integer validationParkingSpace() {
        try {
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
    }

    private int assignParkingSpace(ArrayList<Integer> bookParkingSpace) {
        Integer clientParkingSpace = 1;
        for (Integer parkingSpace : bookParkingSpace) {
            if (clientParkingSpace.equals(parkingSpace))
                ++clientParkingSpace;
        }
        return clientParkingSpace;
    }

    public boolean validationLogging(String login, String password) {
        try {
            String SQL = "SELECT * FROM data WHERE login= ?";
            PreparedStatement preparedStatement = DBHandler.connection.prepareStatement(SQL);
            preparedStatement.setString(1, login);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs == null)
                return false;
            rs.next();
            String hash = DigestUtils.md5Hex(password);
            if (rs.getString("password").equals(hash))
                return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
