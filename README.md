Lee Service
---
To test the following features
- Kafka (partition optimization)
- Circuit breaker and bulkhead
- Integrating with Jacoco
- Integrating & optimizing with Cosmos DB
- Add Coverage to 90%

Configure Kafka
- Download Kafka package from Apache Kafka and follow the instruction https://kafka.apache.org/quickstart
- Run the Zookeeper 
  - ```bin/zookeeper-server-start.sh config/zookeeper.properties```
- Run the Kafka broker 
  - ```bin/kafka-server-start.sh config/server.properties```