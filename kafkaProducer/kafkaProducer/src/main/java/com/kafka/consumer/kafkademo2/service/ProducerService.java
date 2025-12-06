package com.kafka.consumer.kafkademo2.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Service
public class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

   /* public void sendMessage(MessageRequest message) {
        logger.info("Sending message to Kafka topic [{}]: {}", topicName, message);
        kafkaTemplate.send(topicName, message.toString());
        logger.info("Message sent successfully");
    }*/

    public void sendMessage(String key, String message) {

        logger.info("Sending message to topic [{}] with key [{}]: {}",
                topicName, key, message);

        CompletableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topicName, key, message);

        future.whenComplete((result, ex) -> {

            if (ex == null) { // SUCCESS
                logger.info(
                        "Message sent successfully. Topic: {}, Partition: {}, Offset: {}",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset()
                );
            } else { // FAILURE
                logger.error(
                        "Failed to send message to topic [{}] with key [{}]. Error: {}",
                        topicName, key, ex.getMessage(), ex
                );
            }
        });
    }


}
