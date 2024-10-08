package com.minhle.leeservice.controller;

import com.minhle.leeservice.model.Employee;
import com.minhle.leeservice.model.Message;
import com.minhle.leeservice.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/api/employee",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Employee> sendMessage(@RequestBody Employee employee) {
        kafkaService.updateEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @GetMapping(value = "/api/message/{message}")
    public ResponseEntity<?> getMessage(@PathVariable String message) {
        Long count = kafkaService.getMessage(message);
        return ResponseEntity.ok(count);
    }
}
