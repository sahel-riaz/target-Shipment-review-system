package com.target.shipments.service;

import com.target.shipments.model.ShipmentES;
import com.target.shipments.repository.elasticsearch.ShipmentESRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentServiceES {
    @Autowired
    private ShipmentESRepository shipmentESRepository;

    public Iterable<ShipmentES> getShipments(){
        return shipmentESRepository.findAll();
    }

    public ShipmentES insertShipment(ShipmentES shipment) {
        return shipmentESRepository.save(shipment);
    }

    public List<ShipmentES> getShipmentsByStatus(String status) {
        return shipmentESRepository.findByStatus(status);
    }

    public long countShipmentsByStatus(String status) {
        return shipmentESRepository.countByStatus(status);
    }

}
