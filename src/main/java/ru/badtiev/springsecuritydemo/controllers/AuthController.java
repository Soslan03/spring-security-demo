package ru.badtiev.springsecuritydemo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String getLoginPaPage(){
        return "login";
    }
    @GetMapping("/success")
    public String getSuccessPaPage(){
        return "success";
    }
}
