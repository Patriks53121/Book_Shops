package com.example.spring.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String startPage() {
        return "index.html";
    }
    @GetMapping("/index.html")
    public String startPageIndex(){
        return "index.html";
    }


}
