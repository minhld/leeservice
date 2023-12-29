package com.minhle.leeservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaService {
    @Value(value = "${spring.kafka.topic}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory;

    public void sendMessage(String msg) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, msg);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message='{}' with offset={}", msg, result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message='{}' due to exception {}", msg, ex.getMessage());
            }
        });
        kafkaTemplate.send(topicName, msg);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic}",
            groupId = "${spring.kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenWithFilter(String message) {
        log.info("Received Message '{}' in topic '{}'", message, topicName);
    }
}
