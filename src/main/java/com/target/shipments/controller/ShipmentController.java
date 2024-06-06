package com.target.shipments.controller;

import com.target.shipments.entity.Shipment;
import com.target.shipments.entity.User;
import com.target.shipments.service.ShipmentService;
import com.target.shipments.repository.ShipmentRepository;
import com.target.shipments.repository.UserRepository;
import com.target.shipments.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackageRepository packageRepository;

    // Endpoint to process a shipment
    @PostMapping("/process")
    public ResponseEntity<String> processShipment(@RequestBody String shipmentData) {
        try {
            shipmentService.processShipment(shipmentData);
            return ResponseEntity.ok("Shipment processed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing shipment: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable String id) {
        Optional<Shipment> shipment = shipmentRepository.findById(id);
        return shipment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentRepository.findAll();
        return ResponseEntity.ok(shipments);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/packages")
    public ResponseEntity<List<com.target.shipments.entity.Package>> getAllPackages() {
        List<com.target.shipments.entity.Package> packages = packageRepository.findAll();
        return ResponseEntity.ok(packages);
    }
}
