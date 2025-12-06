# Kafka Producer–Consumer Microservices (Spring Boot + Kafka + MySQL)

This project demonstrates a complete **event-driven microservices architecture** using  
**Spring Boot**, **Apache Kafka**, and **MySQL**.

It contains **two separate microservices**:

---

## 📌 1. Producer Service (REST → Kafka)

The Producer exposes a REST API to publish messages to a Kafka topic.

### **Features**
- Accepts REST POST request with a JSON payload  
- Publishes message to Kafka with a key  
- Logs **success** (topic, partition, offset)  
- Logs **failure** (Kafka down / wrong bootstrap server / metadata timeout)  
- Uses `CompletableFuture.whenComplete()` for async success/failure handling

### **Producer API Endpoint**
http://localhost:8081/api/messages


#### Sample Request Body
```json
{
  "key": "user-123",
  "payload": "Hello from Producer Service"
}
------------------------------------------------------------------------------------------------------------------------------

📌 Consumer Service

The Consumer service listens to the same Kafka topic and processes incoming messages.
Every consumed message is persisted into a MySQL database for auditing, tracking, and analytics.

Key Features:

Kafka listener using @KafkaListener

Stores topic, partition, offset, key, value, and timestamp in database

Custom column names to avoid MySQL reserved keywords (key, offset, partition)

REST endpoint to fetch all stored messages

Helpful debug logs for monitoring and tracing

Clean separation of entity, repository, and service layers


