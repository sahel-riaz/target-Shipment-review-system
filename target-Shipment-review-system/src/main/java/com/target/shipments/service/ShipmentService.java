package com.target.shipments.service;

import com.target.shipments.model.Shipment;
import com.target.shipments.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    public Optional<Shipment> getById(String id) {
        return shipmentRepository.findById(id);
    }

    public Shipment save(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public void deleteById(String id) {
        shipmentRepository.deleteById(id);
    }
}
