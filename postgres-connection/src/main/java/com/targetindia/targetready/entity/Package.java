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


    @Id
    @Column(name = "package_id")
    private String pckId;
    private String description;
    @Column(name = "weight")
    private Double weight_kg;
    private Integer length;
    private Integer width;
    private Integer height;

    @Basic
    private Map<String, Integer> dimensions_cm;

    public void setLength(){
        this.length = this.dimensions_cm.get("length");
    }
    public void setWidth(){
        this.width = this.dimensions_cm.get("width");
    }
    public void setHeight(){
        this.height = this.dimensions_cm.get("height");
    }

}
