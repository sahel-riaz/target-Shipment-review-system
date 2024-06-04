package com.targetindia.targetready.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

@Table(name = "location")
public class Location {
   @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id")
    private Integer locationId;
    private String identity; //Sender or Recipient
    private String name;
    private String address;
    private String contact;

}
