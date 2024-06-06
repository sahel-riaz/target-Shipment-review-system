package com.target.shipments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.target.shipments.entity.ShipmentPackage;
import com.target.shipments.entity.ShipmentPackageId;

public interface ShipmentPackageRepository extends JpaRepository<ShipmentPackage, ShipmentPackageId> {
}
