// package com.target.shipments.entity;

// import jakarta.persistence.*;
// // import java.util.List;
// import java.util.Set;


// @Entity
// @Table(name = "shipment")
// public class Shipment {
    
//     @Id
//     @Column(name = "id")
//     private String id;

//     @ManyToOne
//     @JoinColumn(name = "sender_id")
//     private User senderId;

//     @ManyToOne
//     @JoinColumn(name = "recipient_id")
//     private User recipientId;

//     @Column(name = "shipping_method")
//     private String shippingMethod;

//     @Column(name = "delivery_date")
//     private String deliveryDate;

//     @Column(name = "total_cost")
//     private double totalCost;

//     @Column(name = "currency")
//     private String currency;

//     @Column(name = "status")
//     private String status;

//     @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
//     private Set<ShipmentPackage> shipmentPackages;

//     // Getters and Setters
//     public String getId() {
//         return id;
//     }

//     public void setId(String id) {
//         this.id = id;
//     }

//     public User getSenderId() {
//         return senderId;
//     }

//     public void setSenderId(User senderId) {
//         this.senderId = senderId;
//     }

//     public User getRecipientId() {
//         return recipientId;
//     }

//     public void setRecipientId(User recipientId) {
//         this.recipientId = recipientId;
//     }

//     public String getShippingMethod() {
//         return shippingMethod;
//     }

//     public void setShippingMethod(String shippingMethod) {
//         this.shippingMethod = shippingMethod;
//     }

//     public String getDeliveryDate() {
//         return deliveryDate;
//     }

//     public void setDeliveryDate(String deliveryDate) {
//         this.deliveryDate = deliveryDate;
//     }

//     public double getTotalCost() {
//         return totalCost;
//     }

//     public void setTotalCost(double totalCost) {
//         this.totalCost = totalCost;
//     }

//     public String getCurrency() {
//         return currency;
//     }

//     public void setCurrency(String currency) {
//         this.currency = currency;
//     }

//     public String getStatus() {
//         return status;
//     }

//     public void setStatus(String status) {
//         this.status = status;
//     }

//     public Set<ShipmentPackage> getShipmentPackages() {
//         return shipmentPackages;
//     }

//     public void setShipmentPackages(Set<ShipmentPackage> shipmentPackages) {
//         this.shipmentPackages = shipmentPackages;
//     }
// }

package com.target.shipments.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "shipment")
public class Shipment {

    @Id
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id", referencedColumnName = "user_id")
    private User recipient;

    @Column(name = "shipping_method", nullable = false)
    private String shippingMethod;

    @Column(name = "delivery_date", nullable = false)
    private String deliveryDate;

    @Column(name = "total_cost", nullable = false)
    private Double totalCost;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ShipmentPackage> shipmentPackages;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<ShipmentPackage> getShipmentPackages() {
        return shipmentPackages;
    }

    public void setShipmentPackages(Set<ShipmentPackage> shipmentPackages) {
        this.shipmentPackages = shipmentPackages;
    }
}
