package com.target.shipments.controller;

import com.target.shipments.model.ShipmentES;
import com.target.shipments.service.ShipmentServiceES;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ShipmentESController {
    @Autowired
    private ShipmentServiceES shipmentService;

    @GetMapping("/shipments")
    public Iterable<ShipmentES> findAll() {
        return shipmentService.getShipments();
    }

    @GetMapping("/shipments/status")
    public List<ShipmentES> findByStatus(@RequestParam String status) {
        return shipmentService.getShipmentsByStatus(status);
    }

    @GetMapping("/shipments/status/count")
    public long countShipmentsByStatus(@RequestParam String status) {
        return shipmentService.countShipmentsByStatus(status);
    }
}
