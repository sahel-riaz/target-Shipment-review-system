
package com.target.shipments.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public abstract class KafkaListenerService {
    private static final Logger logger = LoggerFactory.getLogger(KafkaListenerService.class);

    private final ShipmentService shipmentService;

    public KafkaListenerService(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @KafkaListener(topics = "shipments")
    public void listen(String message) {
        try {
            shipmentService.processShipment(message);
        } catch (Exception e) {
            logger.error("Error processing message: {}", message, e);
        }
    }

    protected abstract Logger getLogger();
}
