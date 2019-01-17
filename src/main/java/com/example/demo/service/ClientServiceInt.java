package com.example.demo.service;

import com.example.demo.model.Client;

public interface ClientServiceInt {
    void addToDB(Client client);

    Client dataOfClient(String login);
}
