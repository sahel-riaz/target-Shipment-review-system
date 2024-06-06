package com.target.shipments.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class KafkaListenerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);

    @Autowired
    private ShipmentService shipmentService;

    @KafkaListener(topics = "shipments_topic", groupId = "com.target")
    public void listen(String message) {
        try {
            logger.info("Received message: {}", message);
            shipmentService.processShipment(message);
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
        }
    }
}
