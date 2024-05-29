package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.*;

import java.awt.*;
import java.util.Map;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Embeddable
//@ToString(exclude = {"id","msg"})
@Table(name = "package")
public class Package {


    @Column(name = "shipment_id")
    private String shipmentId;
    @Id
    @Column(name = "package_id")
    private String package_id;
    @Column(name = "description")
    private String description;
    @Column(name = "weight")
    private Double weight_kg;
    @Column(name = "length")
    private Integer length;
    @Column(name = "width")
    private Integer width;
    @Column(name = "height")
    private Integer height;

    @Basic
    private Map<String, Integer> dimensions_cm;


    /*public String getDimensions(){
        return "dimensions_cm: {\nlength: " + this.length.toString()
        + ",\nwidth: " + this.width.toString() + ",\nheight: " + this.height.toString();
    }
*/

    @ManyToOne
    @JoinColumn(name = "shipmentId", referencedColumnName = "id")
    private Shipment shipment;
    /*public String toString(){
        return "PackageDetails(Shipment_id=" + getShipmentId() +
                ",\npackageId=" + getPackageId() +
                ",\ndescription=" + getDescription() +
                ",\nweight=" + getWeight() +
                getDimensions() + ")";
    }*/

}
