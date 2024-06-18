package com.target.shipments.entity;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class ShipmentPackageTest {

    @Test
    public void testGettersAndSetters() {
        ShipmentPackage shipmentPackage = new ShipmentPackage();

        // Create and set ShipmentPackageId
        ShipmentPackageId id = new ShipmentPackageId();
        id.setShipmentId("SHIP001");
        id.setPackageId("PKG001");
        shipmentPackage.setId(id);

        // Create and set Shipment
        Shipment shipment = new Shipment();
        shipment.setId("SHIP001");
        shipmentPackage.setShipment(shipment);

        // Create and set Package
        Package pkg = new Package();
        pkg.setPackageId("PKG001");
        shipmentPackage.setPackage(pkg);

        // Asserting values
        assertThat(shipmentPackage.getId()).isEqualTo(id);
        assertThat(shipmentPackage.getShipment()).isEqualTo(shipment);
        assertThat(shipmentPackage.getPackage()).isEqualTo(pkg);
    }

    @Test
    public void testEqualsAndHashCode() {
        ShipmentPackage shipmentPackage1 = new ShipmentPackage();
        ShipmentPackageId id1 = new ShipmentPackageId();
        id1.setShipmentId("SHIP001");
        id1.setPackageId("PKG001");
        shipmentPackage1.setId(id1);

        ShipmentPackage shipmentPackage2 = new ShipmentPackage();
        ShipmentPackageId id2 = new ShipmentPackageId();
        id2.setShipmentId("SHIP001");
        id2.setPackageId("PKG001");
        shipmentPackage2.setId(id2);

        assertThat(shipmentPackage1).isEqualTo(shipmentPackage2);
        assertThat(shipmentPackage1.hashCode()).isEqualTo(shipmentPackage2.hashCode());

        id2.setPackageId("PKG002");
        shipmentPackage2.setId(id2);

        assertThat(shipmentPackage1).isNotEqualTo(shipmentPackage2);
        assertThat(shipmentPackage1.hashCode()).isNotEqualTo(shipmentPackage2.hashCode());
    }
}
