package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "shipment_id")
    private String shipmentId;
    @Column(name = "identity")
    private String identity;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;



    @OneToOne
    @JoinColumn(name = "id")
    private Shipment shipment;
}
