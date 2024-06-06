// package com.target.shipments.entity;

// import jakarta.persistence.*;

// @Entity
// @Table(name = "shipment_package")
// public class ShipmentPackage {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     @Column(name = "id")
//     private Long id;

//     @Column(name = "shipment_id")
//     private String shipmentId;

//     @Column(name = "package_id")
//     private String packageId;

//     @ManyToOne
//     @JoinColumn(name = "shipment_id", insertable = false, updatable = false)
//     private Shipment shipment;

//     @ManyToOne
//     @JoinColumn(name = "package_id", insertable = false, updatable = false)
//     private Package packageEntity;

//     // Getters and Setters
//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

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

//     public Shipment getShipment() {
//         return shipment;
//     }

//     public void setShipment(Shipment shipment) {
//         this.shipment = shipment;
//     }

//     public Package getPackageEntity() {
//         return packageEntity;
//     }

//     public void setPackageEntity(Package packageEntity) {
//         this.packageEntity = packageEntity;
//     }
// }

package com.target.shipments.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "shipment_package")
public class ShipmentPackage {

    @EmbeddedId
    private ShipmentPackageId id;

    @ManyToOne
    @MapsId("shipmentId")
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @ManyToOne
    @MapsId("packageId")
    @JoinColumn(name = "package_id")
    private Package pkg;

    // Getters and Setters

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
