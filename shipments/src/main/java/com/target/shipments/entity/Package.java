// package com.target.shipments.entity;

// import jakarta.persistence.*;
// import java.util.List;

// @Entity
// @Table(name = "package")
// public class Package {

//     @Id
//     @Column(name = "package_id")
//     private String packageId;

//     @Column(name = "description")
//     private String description;

//     @Column(name = "weight")
//     private double weight;

//     @Column(name = "length")
//     private int length;

//     @Column(name = "height")
//     private int height;

//     @Column(name = "width")
//     private int width;

//     @OneToMany(mappedBy = "packageEntity", cascade = CascadeType.ALL)
//     private List<ShipmentPackage> shipmentPackages;

//     // Getters and Setters
//     public String getPackageId() {
//         return packageId;
//     }

//     public void setPackageId(String packageId) {
//         this.packageId = packageId;
//     }

//     public String getDescription() {
//         return description;
//     }

//     public void setDescription(String description) {
//         this.description = description;
//     }

//     public double getWeight() {
//         return weight;
//     }

//     public void setWeight(double weight) {
//         this.weight = weight;
//     }

//     public int getLength() {
//         return length;
//     }

//     public void setLength(int length) {
//         this.length = length;
//     }

//     public int getHeight() {
//         return height;
//     }

//     public void setHeight(int height) {
//         this.height = height;
//     }

//     public int getWidth() {
//         return width;
//     }

//     public void setWidth(int width) {
//         this.width = width;
//     }

//     public List<ShipmentPackage> getShipmentPackages() {
//         return shipmentPackages;
//     }

//     public void setShipmentPackages(List<ShipmentPackage> shipmentPackages) {
//         this.shipmentPackages = shipmentPackages;
//     }
// }

package com.target.shipments.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "package")
public class Package {

    @Id
    @Column(name = "package_id")
    private String packageId;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "height", nullable = false)
    private Integer height;

    @Column(name = "width", nullable = false)
    private Integer width;

    @OneToMany(mappedBy = "pkg", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ShipmentPackage> shipmentPackages;

    // Getters and Setters

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Set<ShipmentPackage> getShipmentPackages() {
        return shipmentPackages;
    }

    public void setShipmentPackages(Set<ShipmentPackage> shipmentPackages) {
        this.shipmentPackages = shipmentPackages;
    }
}
