package com.example.demo.service;

public interface IValidation {
    boolean validationToRegistration(String login);

    Integer validationParkingSpace();

    boolean validationLogging(String login, String password);
}
