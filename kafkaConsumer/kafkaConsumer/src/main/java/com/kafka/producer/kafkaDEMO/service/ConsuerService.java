package com.kafka.producer.kafkaDEMO.service;
import com.kafka.producer.kafkaDEMO.Entity.KafkaMessage;
import com.kafka.producer.kafkaDEMO.Repo.KafkaMessageRepo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ConsuerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsuerService.class);

    private KafkaMessageRepo kafkaMessageRepo;

    public ConsuerService(KafkaMessageRepo kafkaMessageRepo) {
       this.kafkaMessageRepo=kafkaMessageRepo;
    }

   /* @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, String> record) {
        logger.info("Received Message from topic [{}]: {}", record.topic(), record.value());
    }*/

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, String> record) {

        logger.info("Received Message - Topic: {}, Partition: {}, Offset: {}, Key: {}, Value: {}",
                record.topic(), record.partition(), record.offset(), record.key(), record.value());

        KafkaMessage msg = new KafkaMessage();
        msg.setTopic(record.topic());
        msg.setPartition(record.partition());
        msg.setOffset(record.offset());
        msg.setKey(record.key());
        msg.setValue(record.value());
        msg.setReceivedAt(LocalDateTime.now());

        kafkaMessageRepo.save(msg);
        logger.info("Message saved to DB with value: {}", record.value());
    }


}
