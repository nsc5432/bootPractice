package com.backend.sample.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/hello")
@RestController
public class HelloController {
    @GetMapping("welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("hello world");
    }
}
