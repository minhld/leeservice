package com.minhle.leeservice.service;

import com.minhle.leeservice.model.Message;
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
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory;

    public void sendMessage(Message msg) {
        CompletableFuture<SendResult<String, Message>> future = kafkaTemplate.send(topicName, msg);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message='{}' with offset={}", msg.getMessage(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message='{}' due to exception {}", msg.getMessage(), ex.getMessage());
            }
        });
        kafkaTemplate.send(topicName, msg);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic}",
            groupId = "${spring.kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory")
    public void listenWithFilter(Message msg) {
        log.info("Received Message '{}' in topic '{}'", msg.getMessage(), topicName);
    }
}
