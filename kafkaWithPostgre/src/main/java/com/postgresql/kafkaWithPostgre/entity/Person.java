package com.postgresql.kafkaWithPostgre.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="user_table")
public class Person {
    @Id
    private Long id;
    private String firstName;
    private String secondName;
}
