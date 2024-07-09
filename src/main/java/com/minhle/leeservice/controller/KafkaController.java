package com.minhle.leeservice.controller;

import com.minhle.leeservice.model.Message;
import com.minhle.leeservice.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ConditionalOnProperty(value = "spring.kafka.enabled", matchIfMissing = true)
public class KafkaController {
    @Autowired
    KafkaService kafkaService;

    @PostMapping(value = "/api/message",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Message> sendMessage(@RequestBody Message message) {
        kafkaService.sendMessage(message);
        return ResponseEntity.ok(message);
    }

    @GetMapping(value = "/api/message/{message}")
    public ResponseEntity<?> getMessage(@PathVariable String message) {
        Long count = kafkaService.getMessage(message);
        return ResponseEntity.ok(count);
    }
}
