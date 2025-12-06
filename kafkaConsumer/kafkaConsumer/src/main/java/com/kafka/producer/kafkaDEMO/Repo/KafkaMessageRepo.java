package com.kafka.producer.kafkaDEMO.Repo;

import com.kafka.producer.kafkaDEMO.Entity.KafkaMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KafkaMessageRepo extends JpaRepository<KafkaMessage,Long> {
}
