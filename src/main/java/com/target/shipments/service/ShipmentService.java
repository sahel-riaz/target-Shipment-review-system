package com.target.shipments.service;

import com.target.shipments.model.Location;
import com.target.shipments.model.Shipment;
import com.target.shipments.repository.jpa.LocationRepository;
import com.target.shipments.repository.jpa.ShipmentRepository;

// import com.target.shipments.repository.ShipmentESRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class ShipmentService {
    private static final Pattern CURRENCY_PATTERN = Pattern.compile("^[A-Z]{3}$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Logger logger = LoggerFactory.getLogger(ShipmentService.class);
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    // GET Requests
    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    public Optional<Shipment> getById(String id) {
        return shipmentRepository.findById(id);
    }

    public List<Shipment> getByShippingMethod(String shipping_method){return shipmentRepository.findByShippingMethod(shipping_method);}
    public List<Shipment> getByCurrency(String currency){return shipmentRepository.findByCurrency(currency);}
    public List<Shipment> getByStatus(String status){return shipmentRepository.findByStatus(status);}


    public long getShippingMethodCount(String shipping_method){return shipmentRepository.countByShippingMethod(shipping_method);}
    public long getCurrencyCount(String currency){return shipmentRepository.countByCurrency(currency);}
    public long getStatusCount(String status){return shipmentRepository.countByStatus(status);}



    // POST Requests
    public Shipment save(Shipment shipment) {
        validateShipment(shipment);

        if (shipment.getPackage_details() == null) {
            shipment.setPackage_details(Collections.emptyList());
        }

        Location sender = shipment.getSender();
        Location recipient = shipment.getRecipient();

        sender.setIdentity("Sender");
        recipient.setIdentity("Recipient");

        Location findSender = locationRepository.findLocation(
                sender.getName(), sender.getIdentity(), sender.getAddress(), sender.getContact()
        );
        Location findRecipient = locationRepository.findLocation(
                recipient.getName(), recipient.getIdentity(), recipient.getAddress(), recipient.getContact()
        );

        if (findRecipient != null && findSender != null) {
            shipment.setRecipient(findRecipient);
            shipment.setSender(findSender);
        } else if (findSender != null) {
            shipment.setSender(findSender);
        } else if (findRecipient != null) {
            shipment.setRecipient(findRecipient);
        }

        logger.info("Saving shipment: {}", shipment);
        Shipment savedShipment = shipmentRepository.save(shipment);
        logger.info("Shipment saved successfully: {}", savedShipment);

        return savedShipment;
    }

    public void deleteById(String id) {
        shipmentRepository.deleteById(id);
    }

    private void validateShipment(Shipment shipment) {
        validateString(shipment.getShipment_id(), "shipment_id");
        validateString(shipment.getShippingMethod(), "shipping_method");
        validateDate(shipment.getEstimated_delivery_date(), "estimated_delivery_date");
        validateDouble(shipment.getTotal_cost(), "total_cost");
        validateCurrency(shipment.getCurrency(), "currency");
        validateString(shipment.getStatus(), "status");

        if (shipment.getSender() == null) {
            throw new IllegalArgumentException("Sender cannot be null");
        }
        if (shipment.getRecipient() == null) {
            throw new IllegalArgumentException("Recipient cannot be null");
        }

        validateLocation(shipment.getSender(), "sender");
        validateLocation(shipment.getRecipient(), "recipient");

        if (shipment.getPackage_details() != null) {
            shipment.getPackage_details().forEach(pkg -> {
                validateString(pkg.getPackage_id(), "package_id");
                validateString(pkg.getDescription(), "description");
                validateDouble(pkg.getWeight_kg(), "weight_kg");
                validateInt(pkg.getLength(), "dimensions_cm.length");
                validateInt(pkg.getWidth(), "dimensions_cm.width");
                validateInt(pkg.getHeight(), "dimensions_cm.height");
            });
        }
    }

    private void validateLocation(Location location, String fieldNamePrefix) {
        validateString(location.getName(), fieldNamePrefix + ".name");
        validateString(location.getAddress(), fieldNamePrefix + ".address");
        validateString(location.getContact(), fieldNamePrefix + ".contact");
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

