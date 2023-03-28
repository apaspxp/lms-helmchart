package com.example.securityapp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private static  final Logger log = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello")
    public String hello() {
        log.info("inside hello controller");
        return "Hello world from version 1";
    }

}
