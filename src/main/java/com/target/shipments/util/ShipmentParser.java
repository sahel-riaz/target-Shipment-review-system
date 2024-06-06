package com.target.shipments.util;

import com.target.shipments.entity.Package;
import com.target.shipments.entity.Shipment;
import com.target.shipments.entity.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class ShipmentParser {

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

            // Parsing shipment ID
            shipment.setId(root.path("shipment_id").asText());
            System.out.println("Shipment ID: " + shipment.getId());

            // Parse sender
            User sender = new User();
            int senderId = generateRandomUserId();
            sender.setUserId(senderId);
            sender.setName(root.path("sender").path("name").asText());
            sender.setAddress(root.path("sender").path("address").asText());
            sender.setContact(root.path("sender").path("contact").asText());
            users[0] = sender;
            shipment.setSenderId(senderId);
            System.out.println("Sender ID: " + sender.getUserId());
            System.out.println("Sender Name: " + sender.getName());
            System.out.println("Sender Address: " + sender.getAddress());
            System.out.println("Sender Contact: " + sender.getContact());

            User recipient = new User();
            int recipientId = generateRandomUserId();
            recipient.setUserId(recipientId);
            recipient.setName(root.path("recipient").path("name").asText());
            recipient.setAddress(root.path("recipient").path("address").asText());
            recipient.setContact(root.path("recipient").path("contact").asText());
            users[1] = recipient; // Store recipient in array
            shipment.setRecipientId(recipientId);
            System.out.println("Recipient ID: " + recipient.getUserId());
            System.out.println("Recipient Name: " + recipient.getName());
            System.out.println("Recipient Address: " + recipient.getAddress());
            System.out.println("Recipient Contact: " + recipient.getContact());

            shipment.setShippingMethod(root.path("shipping_method").asText());
            System.out.println("Shipping Method: " + shipment.getShippingMethod());
            shipment.setDeliveryDate(root.path("estimated_delivery_date").asText());
            System.out.println("Estimated Delivery Date: " + shipment.getDeliveryDate());
            shipment.setTotalCost(root.path("total_cost").asDouble());
            System.out.println("Total Cost: " + shipment.getTotalCost());
            shipment.setCurrency(root.path("currency").asText());
            System.out.println("Currency: " + shipment.getCurrency());
            shipment.setStatus(root.path("status").asText());
            System.out.println("Status: " + shipment.getStatus());

            JsonNode packagesNode = root.path("package_details");
            for (JsonNode packageNode : packagesNode) {
                Package pkg = new Package();
                pkg.setPackageId(packageNode.path("package_id").asText());
                pkg.setDescription(packageNode.path("description").asText());
                pkg.setWeight(packageNode.path("weight_kg").asDouble());
                pkg.setLength((int) packageNode.path("dimensions_cm").path("length").asDouble());
                pkg.setWidth((int) packageNode.path("dimensions_cm").path("width").asDouble());
                pkg.setHeight((int) packageNode.path("dimensions_cm").path("height").asDouble());
                packages.add(pkg);

                System.out.println("Package ID: " + pkg.getPackageId());
                System.out.println("Description: " + pkg.getDescription());
                System.out.println("Weight: " + pkg.getWeight());
                System.out.println("Length: " + pkg.getLength());
                System.out.println("Width: " + pkg.getWidth());
                System.out.println("Height: " + pkg.getHeight());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Object[] {shipment, users, packages};
    }
}

