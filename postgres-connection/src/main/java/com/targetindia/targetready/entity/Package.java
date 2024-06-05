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
