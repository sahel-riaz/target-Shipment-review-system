package com.target.shipments.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "shipment")

public class Shipment implements Serializable {


    @Id
    private String shipment_id;
    private String shipping_method;
    private String estimated_delivery_date;
    private Double total_cost;
    private String currency;
    private String status;

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sender_id", referencedColumnName = "location_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location sender;

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipient_id", referencedColumnName = "location_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location recipient;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Package> package_details;

    /*@PrePersist
    public void onPrePersist() {
        this.sender.setIdentity("Sender");
        this.recipient.setIdentity("Recipient");
    }

    @PreUpdate
    public void onPreUpdate() {
        this.sender.setIdentity("Sender");
        this.recipient.setIdentity("Recipient");
    }*/
}
