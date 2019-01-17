package com.example.demo.controllers;

import com.example.demo.service.ClientServiceInt;
import com.example.demo.service.ValidationInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @Autowired
    private ValidationInt validationInt;

    @Autowired
    private ClientServiceInt clientServiceInt;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/decision")
    public String decision(@RequestParam String login, @RequestParam String password, ModelMap modelMap) {
        if (!validationInt.validationLogging(login, password))
            return "wrongLogging";
        modelMap.put("client", clientServiceInt.dataOfClient(login));
        return "pageWithData";
    }

    @GetMapping("/decisionForRegistration")
    public String decisionForRegistration() {
        return "registration.html";
    }
}
