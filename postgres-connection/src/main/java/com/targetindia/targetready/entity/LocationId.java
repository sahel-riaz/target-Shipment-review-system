package com.targetindia.targetready.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class LocationId implements Serializable {
    private String shipmentId;
    private String identity;

}
