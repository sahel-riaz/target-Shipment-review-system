package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@ToString(exclude = "id")
//@Embeddable
@Table(name = "location")
public class Location {
    @Id
    @Column(name = "shipment_id")
    private String shipmentId;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;
    @Column(name = "identity")
    private String identity;


    @OneToOne
    @JoinColumn(name = "shipmentId")
    private Shipment shipment;
}
