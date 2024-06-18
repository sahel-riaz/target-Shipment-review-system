package com.target.shipments.entity;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

public class PackageTest {

    @Test
    public void testGettersAndSetters() {
        Package pkg = new Package();

        // Setting values
        pkg.setPackageId("PKG001");
        pkg.setDescription("Electronics");
        pkg.setWeight(2.5);
        pkg.setLength(10);
        pkg.setHeight(20);
        pkg.setWidth(30);

        ShipmentPackage shipmentPackage = new ShipmentPackage();
        Set<ShipmentPackage> shipmentPackages = new HashSet<>();
        shipmentPackages.add(shipmentPackage);
        pkg.setShipmentPackages(shipmentPackages);

        // Asserting values
        assertThat(pkg.getPackageId()).isEqualTo("PKG001");
        assertThat(pkg.getDescription()).isEqualTo("Electronics");
        assertThat(pkg.getWeight()).isEqualTo(2.5);
        assertThat(pkg.getLength()).isEqualTo(10);
        assertThat(pkg.getHeight()).isEqualTo(20);
        assertThat(pkg.getWidth()).isEqualTo(30);
        assertThat(pkg.getShipmentPackages()).isEqualTo(shipmentPackages);
    }

    @Test
    public void testEqualsAndHashCode() {
        Package pkg1 = new Package();
        pkg1.setPackageId("PKG001");

        Package pkg2 = new Package();
        pkg2.setPackageId("PKG001");

        assertThat(pkg1).isEqualTo(pkg2);
        assertThat(pkg1.hashCode()).isEqualTo(pkg2.hashCode());

        pkg2.setPackageId("PKG002");

        assertThat(pkg1).isNotEqualTo(pkg2);
        assertThat(pkg1.hashCode()).isNotEqualTo(pkg2.hashCode());
    }
}
