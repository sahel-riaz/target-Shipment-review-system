

package com.usecase1.shipment_review.payload;

import java.util.List;

public class User {
    private String shipmentId;
    private Person sender;
    private Person recipient;
    private List<PackageDetail> packageDetails;
    private String shippingMethod;
    private String estimatedDeliveryDate;
    private double totalCost;
    private String currency;
    private String status;

    // Getters and Setters

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getRecipient() {
        return recipient;
    }

    public void setRecipient(Person recipient) {
        this.recipient = recipient;
    }

    public List<PackageDetail> getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(List<PackageDetail> packageDetails) {
        this.packageDetails = packageDetails;
    }

    public String getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(String shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Shipment{" +
               "shipmentId='" + shipmentId + '\'' +
               ", sender=" + sender +
               ", recipient=" + recipient +
               ", packageDetails=" + packageDetails +
               ", shippingMethod='" + shippingMethod + '\'' +
               ", estimatedDeliveryDate='" + estimatedDeliveryDate + '\'' +
               ", totalCost=" + totalCost +
               ", currency='" + currency + '\'' +
               ", status='" + status + '\'' +
               '}';
    }
}

class Person {
    private String name;
    private String address;
    private String contact;

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Person{" +
               "name='" + name + '\'' +
               ", address='" + address + '\'' +
               ", contact='" + contact + '\'' +
               '}';
    }
}

class PackageDetail {
    private String packageId;
    private String description;
    private double weightKg;
    private Dimensions dimensionsCm;

    // Getters and Setters

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
    }

    public Dimensions getDimensionsCm() {
        return dimensionsCm;
    }

    public void setDimensionsCm(Dimensions dimensionsCm) {
        this.dimensionsCm = dimensionsCm;
    }

    @Override
    public String toString() {
        return "PackageDetail{" +
               "packageId='" + packageId + '\'' +
               ", description='" + description + '\'' +
               ", weightKg=" + weightKg +
               ", dimensionsCm=" + dimensionsCm +
               '}';
    }
}

class Dimensions {
    private double length;
    private double width;
    private double height;

    // Getters and Setters

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Dimensions{" +
               "length=" + length +
               ", width=" + width +
               ", height=" + height +
               '}';
    }
}
