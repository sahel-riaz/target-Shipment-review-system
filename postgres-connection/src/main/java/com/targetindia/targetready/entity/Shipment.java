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
    @Column(name = "shipping_method")
    private String shipping_method;
    @Column(name = "date")
    private String estimated_delivery_date;
    @Column(name = "cost")
    private Double total_cost;
    @Column(name = "currency")
    private String currency;
    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
    private Location sender;

    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
    private Location recipient;


    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<Package> package_details;

}
