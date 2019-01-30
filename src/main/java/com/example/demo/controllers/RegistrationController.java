package com.example.demo.controllers;

import com.example.demo.model.Client;
import com.example.demo.service.IClientService;
import com.example.demo.service.IDataHash;
import com.example.demo.service.IValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Component
@Controller
public class RegistrationController {

    @Autowired
    private IValidation validationInt;

    @Autowired
    private IClientService clientServiceInt;

    @Autowired
    private IDataHash iDataHash;

    @RequestMapping(method = RequestMethod.GET, value = "/registration")
    public String registration(Model model) {
        model.addAttribute("client", new Client());
        return "/registration";
        }

    @RequestMapping(method = RequestMethod.POST, value = "/registration")
    public String registration2(@ModelAttribute Client client, ModelMap modelMap) {

        if (validationInt.validationToRegistration(client.getLogin()))
            return "registrationBadLogin.html";
        else {
            client.setPassword(iDataHash.hashPassword(client.getPassword()));
            client.setParkingSpace(validationInt.validationParkingSpace());
            clientServiceInt.addToDB(client);
            modelMap.put("client", client);
            return "pageWithData";
        }

    }
}
