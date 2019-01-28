package com.example.demo.service;

import com.example.demo.model.Client;

public interface IClientService {
    void addToDB(Client client);

    Client dataOfClient(String login);
}
