package com.example.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandler {

    public static Connection connection = null;

    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            DBHandler.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/parking?user=root&password=&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
