package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;

@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "package")
public class Package {


    @Column(name = "shipment_id")
    private String shpId;
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


    @ManyToOne
    @JoinColumn(name = "id")
    private Shipment shipment;

}
