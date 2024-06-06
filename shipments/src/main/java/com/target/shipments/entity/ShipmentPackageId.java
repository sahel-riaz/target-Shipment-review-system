// package com.target.shipments.entity;

// import java.io.Serializable;
// import jakarta.persistence.Embeddable;
// import java.util.Objects;

// @Embeddable
// public class ShipmentPackageId implements Serializable {

//     private String shipmentId;
//     private String packageId;

//     // Getters and Setters
//     public String getShipmentId() {
//         return shipmentId;
//     }

//     public void setShipmentId(String shipmentId) {
//         this.shipmentId = shipmentId;
//     }

//     public String getPackageId() {
//         return packageId;
//     }

//     public void setPackageId(String packageId) {
//         this.packageId = packageId;
//     }

//     // hashCode and equals
//     @Override
//     public int hashCode() {
//         return Objects.hash(shipmentId, packageId);
//     }

//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj) return true;
//         if (obj == null || getClass() != obj.getClass()) return false;
//         ShipmentPackageId that = (ShipmentPackageId) obj;
//         return Objects.equals(shipmentId, that.shipmentId) &&
//                Objects.equals(packageId, that.packageId);
//     }
// }

package com.target.shipments.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShipmentPackageId implements Serializable {

    @Column(name = "shipment_id")
    private String shipmentId;

    @Column(name = "package_id")
    private String packageId;

    public ShipmentPackageId() {
    }

    public ShipmentPackageId(String shipmentId, String packageId) {
        this.shipmentId = shipmentId;
        this.packageId = packageId;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShipmentPackageId that = (ShipmentPackageId) o;
        return Objects.equals(shipmentId, that.shipmentId) && Objects.equals(packageId, that.packageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shipmentId, packageId);
    }
}
