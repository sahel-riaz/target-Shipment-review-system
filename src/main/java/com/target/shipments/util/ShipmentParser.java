package com.target.shipments.util;

import com.target.shipments.entity.Package;
import com.target.shipments.entity.Shipment;
import com.target.shipments.entity.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;
import java.util.regex.Pattern;

public class ShipmentParser {

    private static final Pattern CURRENCY_PATTERN = Pattern.compile("^[A-Z]{3}$");
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    private static int generateRandomUserId() {
        Random random = new Random();
        return random.nextInt(10000);
    }

    public static Object[] parseShipment(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Shipment shipment = new Shipment();
        User[] users = new User[2];
        Set<Package> packages = new HashSet<>();

        try {
            JsonNode root = mapper.readTree(json);


            shipment.setId(validateString(root.path("shipment_id").asText(), "shipment_id"));


            User sender = new User();
            int senderId = generateRandomUserId();
            sender.setUserId(senderId);
            sender.setName(validateString(root.path("sender").path("name").asText(), "sender.name"));
            sender.setAddress(validateString(root.path("sender").path("address").asText(), "sender.address"));
            sender.setContact(validateString(root.path("sender").path("contact").asText(), "sender.contact"));
            users[0] = sender;
            shipment.setSenderId(senderId);


            User recipient = new User();
            int recipientId = generateRandomUserId();
            recipient.setUserId(recipientId);
            recipient.setName(validateString(root.path("recipient").path("name").asText(), "recipient.name"));
            recipient.setAddress(validateString(root.path("recipient").path("address").asText(), "recipient.address"));
            recipient.setContact(validateString(root.path("recipient").path("contact").asText(), "recipient.contact"));
            users[1] = recipient;
            shipment.setRecipientId(recipientId);


            shipment.setShippingMethod(validateString(root.path("shipping_method").asText(), "shipping_method"));
            shipment.setDeliveryDate(validateDate(root.path("estimated_delivery_date").asText(), "estimated_delivery_date"));
            shipment.setTotalCost(validateDouble(root.path("total_cost").asDouble(), "total_cost"));
            shipment.setCurrency(validateCurrency(root.path("currency").asText(), "currency"));
            shipment.setStatus(validateString(root.path("status").asText(), "status"));


            JsonNode packagesNode = root.path("package_details");
            for (JsonNode packageNode : packagesNode) {
                Package pkg = new Package();
                pkg.setPackageId(validateString(packageNode.path("package_id").asText(), "package_id"));
                pkg.setDescription(validateString(packageNode.path("description").asText(), "description"));
                pkg.setWeight(validateDouble(packageNode.path("weight_kg").asDouble(), "weight_kg"));
                pkg.setLength(validateInt(packageNode.path("dimensions_cm").path("length").asInt(), "length"));
                pkg.setWidth(validateInt(packageNode.path("dimensions_cm").path("width").asInt(), "width"));
                pkg.setHeight(validateInt(packageNode.path("dimensions_cm").path("height").asInt(), "height"));
                packages.add(pkg);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException("Invalid shipment data", e);
        }
        return new Object[]{shipment, users, packages};
    }

    private static String validateString(String value, String fieldName) throws IllegalArgumentException {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
        return value;
    }

    private static int validateInt(int value, String fieldName) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
        return value;
    }

    private static double validateDouble(double value, String fieldName) throws IllegalArgumentException {
        if (value <= 0) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
        return value;
    }

    private static String validateCurrency(String value, String fieldName) throws IllegalArgumentException {
        if (!CURRENCY_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
        return value;
    }

    private static String validateDate(String value, String fieldName) throws IllegalArgumentException {
        if (!DATE_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Invalid value for " + fieldName);
        }
        return value;
    }
}
