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
@Table(name = "message")

public class Message {
    @Id
    @Column(name = "id")
    private String shipmentId;
    @Column(name = "shipping_method")
    private String shippingMethod;
    @Column(name = "date")
    private String deliveryDate;
    @Column(name = "cost")
    private Integer cost;
    @Column(name = "currency")
    private String currency;
    @Column(name = "status")
    private String status;

    @OneToOne(mappedBy = "message", cascade = CascadeType.ALL)
    //@JoinColumn(name = "shipmentId")
    //@Embedded
    private Sender sender;

    @OneToOne(mappedBy = "message", cascade = CascadeType.ALL)
    //@JoinColumn(name = "shipmentId")
    //@Embedded
    private Recipient recipient;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    //@Embedded
    private List<PackageDetails> packageDetails;

}
