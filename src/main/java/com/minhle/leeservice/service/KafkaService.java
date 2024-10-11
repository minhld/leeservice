package com.minhle.leeservice.service;

import com.minhle.leeservice.model.Employee;
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
import org.springframework.messaging.Message;
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
    private ConcurrentKafkaListenerContainerFactory<String, Employee> kafkaListenerContainerFactory;

    @Autowired
    private StreamsBuilderFactoryBean factoryBean;

    public void updateEmployee(Employee employee) {
        CompletableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(topicName, employee);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message='{}' with offset={}", employee.getId(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message='{}' due to exception {}", employee.getId(), ex.getMessage());
            }
        });
    }

    @KafkaListener(
            topics = "${spring.kafka.topic}",
            groupId = "${spring.kafka.group-id}",
            containerFactory = "kafkaListenerContainerFactory",
            autoStartup = "true")
    public void listener(Employee employee) {
        log.info("Received Message '{}' in topic '{}'", employee.getFirstName(), topicName);
    }

    public Long getMessage(String word) {
        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();
        assert kafkaStreams != null;
        ReadOnlyKeyValueStore<String, Long> counts = kafkaStreams
                .store(StoreQueryParameters.fromNameAndType(streamTableName, QueryableStoreTypes.keyValueStore()));
        return counts.get(word);

    }
}
