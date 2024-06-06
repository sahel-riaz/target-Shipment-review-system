package com.target.shipments.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.target.shipments.entity.Shipment;

public interface ShipmentRepository extends JpaRepository<Shipment, String> {
}
