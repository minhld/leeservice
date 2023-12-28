package com.minhle.leeservice.controller;

import com.minhle.leeservice.model.Message;
import com.minhle.leeservice.model.Route;
import com.minhle.leeservice.service.KafkaService;
import com.minhle.leeservice.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaController {
    @Autowired
    KafkaService kafkaService;

    @PostMapping(value = "/api/msg",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        return ResponseEntity.ok(message);
    }
}
