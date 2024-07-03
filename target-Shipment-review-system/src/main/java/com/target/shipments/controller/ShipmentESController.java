package com.target.shipments.controller;

import com.target.shipments.model.ShipmentES;
import com.target.shipments.service.ShipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ShipmentESController {
    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/shipments")
    public Iterable<ShipmentES> findAll() {
        return shipmentService.getShipments();
    }
}
