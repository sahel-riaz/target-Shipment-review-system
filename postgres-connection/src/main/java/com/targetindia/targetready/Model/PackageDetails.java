package com.targetindia.targetready.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class PackageDetails {
    private String package_id;
    private String description;
    private Double weight_kg;
    private Map<String, Integer> dimensions_cm;
}
