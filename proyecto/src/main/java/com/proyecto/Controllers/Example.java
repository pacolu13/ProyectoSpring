package com.proyecto.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Example {
    @GetMapping("/example")
    public String example() {
        return "Hello, World!";
    }
}
