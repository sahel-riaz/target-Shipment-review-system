package com.usecase1.shipment_review.controller;

import com.usecase1.shipment_review.kafka.KafkaProducer_String;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class MessageController_String {

    private KafkaProducer_String kafkaProducerString;

    public MessageController_String(KafkaProducer_String kafkaProducerString) {
        this.kafkaProducerString = kafkaProducerString;
    }

    // http:localhost:8080/api/v1/kafka/publish?message= Target Corp
    @GetMapping("/publish")
    public ResponseEntity<String> publish(@RequestParam("message") String message){
        kafkaProducerString.sendMessage(message);
        return ResponseEntity.ok("Message sent to the topic");
    }
}
