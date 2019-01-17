package com.example.demo.service;

public interface ValidationInt {
    boolean validationToRegistration(String login);

    Integer validationParkingSpace();

    boolean validationLogging(String login, String password);
}
