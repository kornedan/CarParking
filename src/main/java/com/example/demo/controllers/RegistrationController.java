package com.example.demo.controllers;

import com.example.demo.model.Client;
import com.example.demo.service.ClientServiceInt;
import com.example.demo.service.ValidationInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@Controller
public class RegistrationController {

    @Autowired
    private ValidationInt validationInt;

    @Autowired
    private ClientServiceInt clientServiceInt;

    @GetMapping("/check")
    public String registration(@RequestParam String registrationNumber, @RequestParam String login, @RequestParam String password, ModelMap modelMap) {
        if (validationInt.validationToRegistration(login))
            return "registrationBadLogin.html";
        else {
            Client client = new Client();
            client.setLogin(login);
            client.setPassword(password);
            client.setRegistrationNumber(registrationNumber);
            client.setParkingSpace(validationInt.validationParkingSpace());
            clientServiceInt.addToDB(client);
            modelMap.put("client", client);
            return "pageWithData";
        }
    }
}
