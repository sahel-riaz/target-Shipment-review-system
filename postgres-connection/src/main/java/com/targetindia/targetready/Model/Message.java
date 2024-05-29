package com.targetindia.targetready.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Message {
    private String shipment_id;
    private Person sender;
    private Person recipient;
    private List<PackageDetails> package_details;
    private String shipping_method;
    private Date estimated_delivery_date;
    private Double total_cost;
    private String currency;
    private String status;
}
