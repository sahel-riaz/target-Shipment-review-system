// package com.target.shipments.entity;

// import jakarta.persistence.*;
// import java.util.List;

// @Entity
// @Table(name = "users")
// public class User {

//     @Id
//     @Column(name = "user_id")
//     private int userId; // Assuming user_id is generated manually

//     @Column(name = "name", nullable = false)
//     private String name;

//     @Column(name = "address", nullable = false)
//     private String address;

//     @Column(name = "contact", nullable = false)
//     private String contact;

//     @OneToMany(mappedBy = "senderId", cascade = CascadeType.ALL)
//     private List<Shipment> sentShipments;

//     @OneToMany(mappedBy = "recipientId", cascade = CascadeType.ALL)
//     private List<Shipment> receivedShipments;

//     // Getters and Setters
//     public int getUserId() {
//         return userId;
//     }

//     public void setUserId(int userId) {
//         this.userId = userId;
//     }

//     public String getName() {
//         return name;
//     }

//     public void setName(String name) {
//         this.name = name;
//     }

//     public String getAddress() {
//         return address;
//     }

//     public void setAddress(String address) {
//         this.address = address;
//     }

//     public String getContact() {
//         return contact;
//     }

//     public void setContact(String contact) {
//         this.contact = contact;
//     }

//     public List<Shipment> getSentShipments() {
//         return sentShipments;
//     }

//     public void setSentShipments(List<Shipment> sentShipments) {
//         this.sentShipments = sentShipments;
//     }

//     public List<Shipment> getReceivedShipments() {
//         return receivedShipments;
//     }

//     public void setReceivedShipments(List<Shipment> receivedShipments) {
//         this.receivedShipments = receivedShipments;
//     }
// }

package com.target.shipments.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "contact", nullable = false)
    private String contact;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Shipment> sentShipments;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Shipment> receivedShipments;

    // Getters and Setters

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
