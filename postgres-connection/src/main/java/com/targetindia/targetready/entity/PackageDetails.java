package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Embeddable
//@ToString(exclude = {"id","msg"})
@Table(name = "package_details")
public class PackageDetails {
    @Column(name = "id")
    private String shipmentid;
    @Id
    @Column(name = "package_id")
    private String packageId;
    @Column(name = "description")
    private String description;
    @Column(name = "weight")
    private double weight;
    @Column(name = "length")
    private Integer length;
    @Column(name = "width")
    private Integer width;
    @Column(name = "height")
    private Integer height;


    public String getDimensions(){
        return "dimensions_cm: {\nlength: " + this.length.toString()
        + ",\nwidth: " + this.width.toString() + ",\nheight: " + this.height.toString();
    }


    @ManyToOne
    @JoinColumn(name = "shipmentid")
    private Message msg;
    public String toString(){
        return "PackageDetails(Shipment_id=" + getShipmentid() +
                ",\npackageId=" + getPackageId() +
                ",\ndescription=" + getDescription() +
                ",\nweight=" + getWeight() +
                getDimensions() + ")";
    }
}
