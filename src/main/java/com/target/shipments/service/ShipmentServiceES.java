package com.target.shipments.service;

import com.target.shipments.model.ShipmentES;
import com.target.shipments.model.Sender;
import com.target.shipments.model.Recipient;
import com.target.shipments.model.PackageDetail;
import com.target.shipments.repository.elasticsearch.ShipmentESRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class ShipmentServiceES {
    private static final Pattern CURRENCY_PATTERN = Pattern.compile("^[A-Z]{3}$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Logger logger = LoggerFactory.getLogger(ShipmentServiceES.class);

    @Autowired
    private ShipmentESRepository shipmentESRepository;

    public Iterable<ShipmentES> getShipments() {
        return shipmentESRepository.findAll();
    }

    public ShipmentES insertShipment(ShipmentES shipment) {
        if (shipment == null) {
            throw new IllegalArgumentException("Shipment must not be null");
        }
        validateShipment(shipment);
        return shipmentESRepository.save(shipment);
    }


    public List<ShipmentES> getShipmentsByStatus(String status) {
        return shipmentESRepository.findByStatus(status);
    }

    public long countShipmentsByStatus(String status) {
        return shipmentESRepository.countByStatus(status);
    }

    public List<ShipmentES> getShipmentsByCurrency(String currency) {
        return shipmentESRepository.findByCurrency(currency);
    }

    public void deleteShipmentById(String shipmentId) {
        shipmentESRepository.deleteByShipmentId(shipmentId);
    }

    private void validateShipment(ShipmentES shipment) {
        validateString(shipment.getShipmentId(), "shipmentId");
        validateString(shipment.getShipping_method(), "shipping_method");
        validateDate(shipment.getEstimated_delivery_date(), "estimated_delivery_date");
        validateDouble(Double.parseDouble(shipment.getTotal_cost()), "total_cost");
        validateCurrency(shipment.getCurrency(), "currency");
        validateString(shipment.getStatus(), "status");

        validateSender(shipment.getSender(), "sender");
        validateRecipient(shipment.getRecipient(), "recipient");

        if (shipment.getPackage_details() != null) {
            shipment.getPackage_details().forEach(pkg -> {
                validateString(pkg.getPackage_id(), "package_id");
                validateString(pkg.getDescription(), "description");
                validateDouble(pkg.getWeight_kg(), "weight_kg");
                validateInt(pkg.getDimensions_cm().getLength(), "dimensions_cm.length");
                validateInt(pkg.getDimensions_cm().getWidth(), "dimensions_cm.width");
                validateInt(pkg.getDimensions_cm().getHeight(), "dimensions_cm.height");
            });
        }
    }

    private void validateSender(Sender sender, String fieldNamePrefix) {
        if (sender != null) {
            validateLocation(sender, fieldNamePrefix);
        }
    }

    private void validateRecipient(Recipient recipient, String fieldNamePrefix) {
        if (recipient != null) {
            validateLocation(recipient, fieldNamePrefix);
        }
    }

    private void validateLocation(Sender sender, String fieldNamePrefix) {
        validateString(sender.getName(), fieldNamePrefix + ".name");
        validateString(sender.getAddress(), fieldNamePrefix + ".address");
        validateString(sender.getContact(), fieldNamePrefix + ".contact");
    }

    private void validateLocation(Recipient recipient, String fieldNamePrefix) {
        validateString(recipient.getName(), fieldNamePrefix + ".name");
        validateString(recipient.getAddress(), fieldNamePrefix + ".address");
        validateString(recipient.getContact(), fieldNamePrefix + ".contact");
    }

    private void validateString(String value, String fieldName) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
    }

    private void validateInt(int value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
    }

    private void validateDouble(double value, String fieldName) {
        if (value <= 0) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
    }

    private void validateCurrency(String value, String fieldName) {
        if (!CURRENCY_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
    }

    private void validateDate(String value, String fieldName) {
        if (!DATE_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
    }
}
