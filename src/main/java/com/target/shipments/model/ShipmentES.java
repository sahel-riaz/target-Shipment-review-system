package com.target.shipments.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.persistence.Column;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "shipments")
public class ShipmentES {
    @Id
    // @Column(name = "shipment_id")
    private String shipmentId;
    private Sender sender;
    private Recipient recipient;
    private List<PackageDetail> package_details;
    private String shipping_method;
    private String estimated_delivery_date;
    private String total_cost;
    private String currency;
    private String status;
    public ShipmentES(String shipmentId, String shipping_method, String estimated_delivery_date, String total_cost, String currency, String status, Sender sender, Recipient recipient) {
        this.shipmentId = shipmentId;
        this.shipping_method = shipping_method;
        this.estimated_delivery_date = estimated_delivery_date;
        this.total_cost = total_cost;
        this.currency = currency;
        this.status = status;
        this.sender = sender;
        this.recipient = recipient;
    }
}
