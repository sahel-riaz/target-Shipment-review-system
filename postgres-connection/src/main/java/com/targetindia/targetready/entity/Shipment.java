package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipment")

public class Shipment {

    @Id
    @Column(name = "id")
    private String shipment_id;
    private String shipping_method;
    @Column(name = "date")
    private String estimated_delivery_date;
    @Column(name = "cost")
    private Double total_cost;
    private String currency;
    private String status;

    @JdbcTypeCode(SqlTypes.JSON)
    private Location sender;
    @JdbcTypeCode(SqlTypes.JSON)
    private Location recipient;
   

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shpId", referencedColumnName = "id")
    private List<Location> location = new ArrayList<>(List.of(sender, recipient));


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shpId", referencedColumnName = "id")
    private List<Package> package_details;

}
