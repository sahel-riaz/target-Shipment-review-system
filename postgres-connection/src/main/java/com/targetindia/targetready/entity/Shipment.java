package com.targetindia.targetready.entity;

import com.targetindia.targetready.Model.PackageDetails;
import com.targetindia.targetready.Model.Person;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")

public class Shipment {

    @Id
    @Column(name = "id")
    private String shipment_id;
    @Column(name = "shipping_method")
    private String shipping_method;
    @Column(name = "date")
    private Date estimated_delivery_date;
    @Column(name = "cost")
    private Double total_cost;
    @Column(name = "currency")
    private String currency;
    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
    //@JoinColumn(name = "shipmentId")
    //@Embedded
    private Location sender;

    @OneToOne(mappedBy = "shipment", cascade = CascadeType.ALL)
    private Location recipient;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    //@Embedded
    private List<Package> package_details;

}
