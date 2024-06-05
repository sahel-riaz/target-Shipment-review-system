package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.ArrayList;
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
    private User sender;
    @JdbcTypeCode(SqlTypes.JSON)
    private User recipient;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id")
    private List<User> users = new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id")
    private List<Package> package_details;

    @PrePersist
    @PreUpdate
    private void setUsers(){
        if (sender != null){
            sender.setIdentity("Sender");
            this.users.add(this.sender);
        }
        if (recipient != null) {
            recipient.setIdentity("Recipient");
            this.users.add(this.recipient);
        }
    }
}
