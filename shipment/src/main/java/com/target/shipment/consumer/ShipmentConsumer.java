package com.target.shipment.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.shipment.entity.Shipment;
import com.target.shipment.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ShipmentConsumer {

    @Autowired
    private ShipmentRepository shipmentRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "shipments_topic", groupId = "com.target")
    public void consume(String message) {
        try {
            Shipment shipment = objectMapper.readValue(message, Shipment.class);
            shipmentRepository.save(shipment);
            System.out.println("Shipment saved: " + shipment.getShipmentId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
