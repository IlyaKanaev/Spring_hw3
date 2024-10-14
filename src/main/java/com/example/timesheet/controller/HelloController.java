package com.example.timesheet.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    // http://localhost:8080/hello?username=Igor
    @GetMapping("/hello")
    public String helloPage(@RequestParam("username") String un) {
        return "<h1>Hello, " + un + "!</h1>";
    }

    // GET http://localhost:8080/home/igor
    // GET http://localhost:8080/home/alex
    @GetMapping("/hello/{username}")
    public String helloPagePathVariable(@PathVariable("username") String un) {
        return "<h1>Hello, " + un + "!</h1>";
    }
}
