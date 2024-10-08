package com.minhle.leeservice.service;

import com.minhle.leeservice.model.Employee;
import com.minhle.leeservice.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@ConditionalOnProperty(value = "spring.kafka.enabled", matchIfMissing = true)
public class KafkaService {
    @Value(value = "${spring.kafka.topic}")
    private String topicName;
    @Value(value = "${spring.kafka.streams.table-name}")
    private String streamTableName;

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;

    @Autowired
    private ConcurrentKafkaListenerContainerFactory<String, Message> kafkaListenerContainerFactory;

    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

//    @Autowired
//    private Processor processor;

    public void sendMessage(Message msg) {
//        CompletableFuture<SendResult<String, Message>> future = kafkaTemplate.send(topicName, msg);
//        future.whenComplete((result, ex) -> {
//            if (ex == null) {
//                log.info("Sent message='{}' with offset={}", msg.getMessage(), result.getRecordMetadata().offset());
//            } else {
//                log.error("Unable to send message='{}' due to exception {}", msg.getMessage(), ex.getMessage());
//            }
//        });
//        kafkaTemplate.send(topicName, msg);
    }

    public void updateEmployee(Employee employee) {
        org.springframework.messaging.Message<Employee> message = MessageBuilder.withPayload(employee).build();
//        processor.output().send(message);
        CompletableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(topicName, employee);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message='{}' with offset={}", employee.getId(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message='{}' due to exception {}", employee.getId(), ex.getMessage());
            }
        });
        kafkaTemplate.send(topicName, employee);
    }

    @KafkaListener(
            topics = "${spring.kafka.topic}",
            groupId = "${spring.kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "true")
    public void listener(Message msg) {
        log.info("Received Message '{}' in topic '{}'", msg.getMessage(), topicName);
    }

    public Long getMessage(String word) {
        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        assert kafkaStreams != null;
        ReadOnlyKeyValueStore<String, Long> counts = kafkaStreams
                .store(StoreQueryParameters.fromNameAndType(streamTableName, QueryableStoreTypes.keyValueStore()));
        return counts.get(word);

    }
}
