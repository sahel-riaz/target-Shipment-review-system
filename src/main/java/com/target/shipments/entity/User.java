package com.target.shipments.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contact", nullable = false)
    private String contact;

    @OneToMany(mappedBy = "senderId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Shipment> sentShipments;

    @OneToMany(mappedBy = "recipientId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Shipment> receivedShipments;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Set<Shipment> getSentShipments() {
        return sentShipments;
    }

    public void setSentShipments(Set<Shipment> sentShipments) {
        this.sentShipments = sentShipments;
    }

    public Set<Shipment> getReceivedShipments() {
        return receivedShipments;
    }

    public void setReceivedShipments(Set<Shipment> receivedShipments) {
        this.receivedShipments = receivedShipments;
    }
}
