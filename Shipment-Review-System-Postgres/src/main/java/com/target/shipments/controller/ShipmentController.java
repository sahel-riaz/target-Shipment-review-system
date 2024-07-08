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


    //GET Requests
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

    @GetMapping("/status")
    public List<Shipment> getShipmentsByStatus(@RequestParam String status) {
        return shipmentService.getByStatus(status);
    }

    @GetMapping("/currency")
    public List<Shipment> getShipmentsByCurrency(@RequestParam String currency) {
        return shipmentService.getByCurrency(currency);
    }

    @GetMapping("/shipping_method")
    public List<Shipment> getShipmenByShpMethod(@RequestParam String shipping_method) {
        return shipmentService.getByShippingMethod(shipping_method);
    }


    @GetMapping("/count/status")
    public long getShipmentStatusCount(@RequestParam String status) {
        return shipmentService.getStatusCount(status);
    }

    @GetMapping("/count/currency")
    public long getShipmentCurrencyCount(@RequestParam String currency) {
        return shipmentService.getCurrencyCount(currency);
    }

    @GetMapping("/count/shipping_method")
    public long getShipmenShpMethodCount(@RequestParam String shipping_method) {
        return shipmentService.getShippingMethodCount(shipping_method);
    }



//POST Requests
    @PostMapping
    public Shipment create(@RequestBody Shipment shipment) {
        return shipmentService.save(shipment);
    }


    //PUT Requests
    @PutMapping ("/{id}")
    public ResponseEntity<Shipment> update(@PathVariable String id) {
        if (shipmentService.getById(id).isPresent()) {
            Shipment shipment = shipmentService.getById(id).get();
            shipment.setShipment_id(id);
            return ResponseEntity.ok(shipmentService.save(shipment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping ("/{id}/shipping_method/{shipping_method}")
    public ResponseEntity<Shipment> updateShippingMethod(@PathVariable String id, @PathVariable String shipping_method) {
        if (shipmentService.getById(id).isPresent()) {
            Shipment shipment = shipmentService.getById(id).get();
            shipment.setShippingMethod(shipping_method);
            return ResponseEntity.ok(shipmentService.save(shipment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping ("/{id}/currency/{currency}")
    public ResponseEntity<Shipment> updateCurrency(@PathVariable String id, @PathVariable String currency) {
        if (shipmentService.getById(id).isPresent()) {
            Shipment shipment = shipmentService.getById(id).get();
            shipment.setCurrency(currency);
            return ResponseEntity.ok(shipmentService.save(shipment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping ("/{id}/status/{status}")
    public ResponseEntity<Shipment> updateStatus(@PathVariable String id, @PathVariable String status) {
        if (shipmentService.getById(id).isPresent()) {
            Shipment shipment = shipmentService.getById(id).get();
            shipment.setStatus(status);
            return ResponseEntity.ok(shipmentService.save(shipment));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//DELETE Requests
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (shipmentService.getById(id).isPresent()) {
            shipmentService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
