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



