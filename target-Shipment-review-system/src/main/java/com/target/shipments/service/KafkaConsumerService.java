
package com.target.shipments.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.shipments.model.Shipment;
import com.target.shipments.repository.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);

    @Autowired
    private ShipmentRepository shipmentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "shipments_topic", groupId = "com.target")
    public void consume(String message) {
        logger.info("Received message: {}", message);
        try {
            Shipment shipment = objectMapper.readValue(message, Shipment.class);
            if (shipment.getShipment_id() != null) {
                shipmentRepository.save(shipment);
                logger.info("Message consumed and saved: {}", shipment);
            } else {
                logger.warn("testId is null, skipping save.");
            }
        } catch (Exception e) {
            logger.error("Error processing message", e);
        }
    }
}
