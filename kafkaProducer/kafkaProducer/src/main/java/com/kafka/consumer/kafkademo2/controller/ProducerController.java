package com.kafka.consumer.kafkademo2.controller;

import com.kafka.consumer.kafkademo2.dto.MessageRequest;
import com.kafka.consumer.kafkademo2.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @PostMapping("/api/messages")
    public ResponseEntity<String>sendMessage(@RequestBody MessageRequest message){

        producerService.sendMessage(message.getKey(),message.getPayload());
        return  ResponseEntity.ok("Message sent to Kafka");
    }
}
