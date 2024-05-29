package com.target.shipment.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @Column(name = "shipment_id", length = 20, nullable = false)
    private String shipmentId;

    @Column(name = "sender", columnDefinition = "jsonb")
    @JsonProperty("sender")
    private String sender;

    @Column(name = "recipient", columnDefinition = "jsonb")
    @JsonProperty("recipient")
    private String recipient;

    @Column(name = "package_details", columnDefinition = "jsonb")
    @JsonProperty("package_details")
    private String packageDetails;

    @Column(name = "shipping_method", length = 20)
    @JsonProperty("shipping_method")
    private String shippingMethod;

    @Column(name = "estimated_delivery_date")
    @JsonProperty("estimated_delivery_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate estimatedDeliveryDate;

    @Column(name = "total_cost")
    @JsonProperty("total_cost")
    private BigDecimal totalCost;

    @Column(name = "currency", length = 10)
    @JsonProperty("currency")
    private String currency;

    @Column(name = "status", length = 20)
    @JsonProperty("status")
    private String status;

    // Getters and Setters
    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(String packageDetails) {
        this.packageDetails = packageDetails;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
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
}
