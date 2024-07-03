package com.target.shipments.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import java.time.LocalDate;
import java.util.List;

// import com.target.shipments.model.PackageDetail;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(indexName = "shipments")
public class ShipmentES {
    @Id
    private String shipment_id;
    private Location sender; 
    private Location recipient;
    private List<PackageDetail> package_details;
    private String shipping_method;
    private LocalDate estimated_delivery_date;
    private double total_cost;
    private String currency;
    private String status;
}

