package com.jujulioed.healthcoreapi.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/apihealthcheck")
public class APIHealthCheck {

    @GetMapping
    public ResponseEntity<String> imAlive() {
        return ResponseEntity.ok("The API is running and available");
    }
}
