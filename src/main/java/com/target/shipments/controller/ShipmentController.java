package com.target.shipments.controller;

import com.target.shipments.model.Shipment;
import com.target.shipments.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/shipment")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @GetMapping
    public List<Shipment> getAll() {
        return shipmentService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getById(@PathVariable String id) {
        Optional<Shipment> shipment = shipmentService.getById(id);
        if (shipment.isPresent()) {
            return ResponseEntity.ok(shipment.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Shipment create(@RequestBody Shipment shipment) {
        return shipmentService.save(shipment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Shipment> update(@PathVariable String id, @RequestBody Shipment shipment) {
        if (shipmentService.getById(id).isPresent()) {
            shipment.setShipment_id(id);
            return ResponseEntity.ok(shipmentService.save(shipment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (shipmentService.getById(id).isPresent()) {
            shipmentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count/{shippingMethod}")
    public long getShipmenShpMethodCount(@RequestParam String shippingMethod) {
        return shipmentService.getShippingMethodCount(shippingMethod);
    }

}