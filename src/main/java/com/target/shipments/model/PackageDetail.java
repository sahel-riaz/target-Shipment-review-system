package com.target.shipments.model;

import lombok.Data;

@Data
public class PackageDetail {
    private String package_id;
    private String description;
    private double weight_kg;
    private Dimensions dimensions_cm;
}
