package com.minhle.leeservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @PostMapping(value = "/api/routes",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> sendMessage(@RequestBody Integer routeNo) {
        return ResponseEntity.ok("");
    }
}
