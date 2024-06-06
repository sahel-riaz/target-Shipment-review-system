package com.target.shipments.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shipment_package")
public class ShipmentPackage {

    @EmbeddedId
    private ShipmentPackageId id;

    @ManyToOne
    @JoinColumn(name = "shipment_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Shipment shipment;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id", insertable = false, updatable = false)
    private Package pkg;

    public ShipmentPackageId getId() {
        return id;
    }

    public void setId(ShipmentPackageId id) {
        this.id = id;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public void setShipment(Shipment shipment) {
        this.shipment = shipment;
    }

    public Package getPackage() {
        return pkg;
    }

    public void setPackage(Package pkg) {
        this.pkg = pkg;
    }
}
