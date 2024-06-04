package com.target.shipments.model;

import jakarta.persistence.*;

@Entity
@Table(name = "test_table")
public class TestTable {
    @Id
    @Column(name = "test_id", length = 10)
    private String testId;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "age", length = 10)
    private String age;


    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
