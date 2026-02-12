package com.gabriel.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//JUST FOR HOMEPAGE

@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "API PAYMENT SERVICE IS RUNNING";
    }
}
