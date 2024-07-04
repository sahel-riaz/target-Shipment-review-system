package com.target.shipments.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dimensions {
    private double length;
    private double width;
    private double height;
}
