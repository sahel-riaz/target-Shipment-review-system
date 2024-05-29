package com.target.shipment.controller;

import com.target.shipment.entity.Shipment;
import com.target.shipment.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @GetMapping
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Shipment> getShipmentById(@PathVariable String id) {
        return shipmentRepository.findById(id);
    }
}
