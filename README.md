Lee Service
---
To test the following features
- Kafka (partition optimization)
- Circuit breaker and bulkhead
- Integrating with Jacoco
- Integrating & optimizing with Cosmos DB
- Add Coverage to 90%

Configure Cassandra
- Using local cassandra

Configure Kafka
- Download Kafka package from Apache Kafka and follow the instruction https://kafka.apache.org/quickstart
- Run the Zookeeper 
  - ```bin/zookeeper-server-start.sh config/zookeeper.properties```
- Run the Kafka broker 
  - ```bin/kafka-server-start.sh config/server.properties```
- Reset index
  - ```bin/kafka-consumer-groups.sh --bootstrap-server localhost:9092 --group minh-group --topic user-message-topic --execute --reset-offsets --to-earliest```
- Delete topic
  - ```bin/kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic user-message-topic```
- To start Schema Registry
  - Download Confluence package
  - ```bin/confluent local services start```