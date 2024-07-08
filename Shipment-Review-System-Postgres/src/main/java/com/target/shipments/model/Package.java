package com.target.shipments.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "package")
@ToString(exclude = "shipment")
public class Package {
    @Id
    private String package_id;
    private String description;
    private Double weight_kg;
    private Integer length;
    private Integer width;
    private Integer height;

    @Basic
    private Map<String, Integer> dimensions_cm;


    /*@PrePersist
    public void onPrePersist() {
        this.length = dimensions_cm.get("length");
        this.width = dimensions_cm.get("width");
        this.height = dimensions_cm.get("height");
    }

    @PreUpdate
    public void onPreUpdate() {
        this.length = dimensions_cm.get("length");
        this.width = dimensions_cm.get("width");
        this.height = dimensions_cm.get("height");
    }*/

    @PrePersist
    @PreUpdate
    private void setDimensions() {
        if (dimensions_cm != null) {
            this.length = dimensions_cm.get("length");
            this.width = dimensions_cm.get("width");
            this.height = dimensions_cm.get("height");
        }
    }
}