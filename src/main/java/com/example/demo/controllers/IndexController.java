package com.example.demo.controllers;

import com.example.demo.service.IClientService;
import com.example.demo.service.IValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private IValidation validationInt;

    @Autowired
    private IClientService clientServiceInt;

    @RequestMapping("/")
    public String index() {
        return "index.html";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/decision")
    public String decision(@RequestParam String login, @RequestParam String password, ModelMap modelMap) {
        if (!validationInt.validationLogging(login, password))
            return "wrongLogging";
        modelMap.put("client", clientServiceInt.dataOfClient(login));
        return "pageWithData";
    }

    /*@RequestMapping(method = RequestMethod.POST, value = "/decisionForRegistration")
    public String decisionForRegistration() {
        return "registration.html";
    }*/
}
