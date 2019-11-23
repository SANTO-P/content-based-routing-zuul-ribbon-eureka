package com.example.domainaccounts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @Autowired
    private Environment environment;

    @RequestMapping(value = "/{account-id}", method = RequestMethod.GET)
    public String getCustomerAccountBalance(@PathVariable(value = "account-id") String accountId) {
        String serverPort = environment.getProperty("local.server.port");
        return "domain-accounts running at port: " + serverPort;
    }
}
