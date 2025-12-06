package com.kafka.producer.kafkaDEMO.controller;
import com.kafka.producer.kafkaDEMO.Entity.KafkaMessage;
import com.kafka.producer.kafkaDEMO.Repo.KafkaMessageRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {


    private final KafkaMessageRepo kafkaMessageRepo;

    public ConsumerController(KafkaMessageRepo kafkaMessageRepo) {
        this.kafkaMessageRepo = kafkaMessageRepo;
    }

    @GetMapping("/messages")
    public List<KafkaMessage>getAllMessages(){
        return kafkaMessageRepo.findAll();
    }

}
