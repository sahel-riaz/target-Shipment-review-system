package com.target.shipments.util;

// import com.target.shipments.entity.Package;

// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.target.shipments.entity.*;

// import java.util.HashSet;
// import java.util.Set;
// import java.util.Random;

// public class ShipmentParser {

//     private static int generateRandomUserId() {
//         Random random = new Random();
//         return random.nextInt(10000); // Adjust as needed
//     }

//     public static Shipment parseShipment(String json) {
//         ObjectMapper mapper = new ObjectMapper();
//         Shipment shipment = new Shipment();

//         try {
//             JsonNode root = mapper.readTree(json);

//             shipment.setId(root.path("shipment_id").asText());

//             // User sender = new User();
//             // JsonNode senderNode = root.path("sender");
//             // sender.setName(senderNode.path("name").asText());
//             // sender.setAddress(senderNode.path("address").asText());
//             // sender.setContact(senderNode.path("contact").asText());
//             // shipment.setSenderId(sender);

//             User sender = new User();
//             sender.setUserId(generateRandomUserId()); 
//             sender.setName(root.path("sender").path("name").asText());
//             sender.setAddress(root.path("sender").path("address").asText());
//             sender.setContact(root.path("sender").path("contact").asText());
//             shipment.setSenderId(sender);


//             // User recipient = new User();
//             // JsonNode recipientNode = root.path("recipient");
//             // recipient.setName(recipientNode.path("name").asText());
//             // recipient.setAddress(recipientNode.path("address").asText());
//             // recipient.setContact(recipientNode.path("contact").asText());
//             // shipment.setRecipientId(recipient);

//             User recipient = new User();
//             recipient.setUserId(generateRandomUserId());
//             recipient.setName(root.path("recipient").path("name").asText());
//             recipient.setAddress(root.path("recipient").path("address").asText());
//             recipient.setContact(root.path("recipient").path("contact").asText());
//             shipment.setRecipientId(recipient);

//             shipment.setShippingMethod(root.path("shipping_method").asText());
//             shipment.setDeliveryDate(root.path("estimated_delivery_date").asText());
//             shipment.setTotalCost(root.path("total_cost").asDouble());
//             shipment.setCurrency(root.path("currency").asText());
//             shipment.setStatus(root.path("status").asText());

//             Set<ShipmentPackage> shipmentPackages = new HashSet<>();
//             JsonNode packagesNode = root.path("package_details");
//             for (JsonNode packageNode : packagesNode) {
//                 Package pkg = new Package();
//                 pkg.setPackageId(packageNode.path("package_id").asText());
//                 pkg.setDescription(packageNode.path("description").asText());
//                 pkg.setWeight(packageNode.path("weight_kg").asDouble());
//                 pkg.setLength((int) packageNode.path("dimensions_cm").path("length").asDouble());
//                 pkg.setWidth((int) packageNode.path("dimensions_cm").path("width").asDouble());
//                 pkg.setHeight((int) packageNode.path("dimensions_cm").path("height").asDouble());

//                 ShipmentPackage shipmentPackage = new ShipmentPackage();
//                 shipmentPackage.setShipment(shipment);
//                 shipmentPackage.setPackageEntity(pkg);
//                 shipmentPackages.add(shipmentPackage);
//             }
//             shipment.setShipmentPackages(shipmentPackages);

//         } catch (Exception e) {
//             e.printStackTrace();
//             // Add proper exception handling here
//         }
//         return shipment;
//     }
// }

import com.target.shipments.entity.Package;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.shipments.entity.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Random;

public class ShipmentParser {

    private static int generateRandomUserId() {
        Random random = new Random();
        return random.nextInt(10000); // Adjust as needed
    }

    public static Shipment parseShipment(String json) {
        ObjectMapper mapper = new ObjectMapper();
        Shipment shipment = new Shipment();

        try {
            JsonNode root = mapper.readTree(json);

            shipment.setId(root.path("shipment_id").asText());
            // System.out.println("Shipment ID: " + shipment.getId());

            User sender = new User();
            int senderId = generateRandomUserId();
            sender.setUserId(senderId);
            sender.setName(root.path("sender").path("name").asText());
            sender.setAddress(root.path("sender").path("address").asText());
            sender.setContact(root.path("sender").path("contact").asText());
            shipment.setSender(sender);
            // System.out.println("Sender ID: " + senderId);
            // System.out.println("Sender Name: " + sender.getName());
            // System.out.println("Sender Address: " + sender.getAddress());
            // System.out.println("Sender Contact: " + sender.getContact());

            User recipient = new User();
            int recipientId = generateRandomUserId();
            recipient.setUserId(recipientId);
            recipient.setName(root.path("recipient").path("name").asText());
            recipient.setAddress(root.path("recipient").path("address").asText());
            recipient.setContact(root.path("recipient").path("contact").asText());
            shipment.setRecipient(recipient);
            // System.out.println("Recipient ID: " + recipientId);
            // System.out.println("Recipient Name: " + recipient.getName());
            // System.out.println("Recipient Address: " + recipient.getAddress());
            // System.out.println("Recipient Contact: " + recipient.getContact());

            shipment.setShippingMethod(root.path("shipping_method").asText());
            // System.out.println("Shipping Method: " + shipment.getShippingMethod());
            shipment.setDeliveryDate(root.path("estimated_delivery_date").asText());
            // System.out.println("Estimated Delivery Date: " + shipment.getDeliveryDate());
            shipment.setTotalCost(root.path("total_cost").asDouble());
            // System.out.println("Total Cost: " + shipment.getTotalCost());
            shipment.setCurrency(root.path("currency").asText());
            // System.out.println("Currency: " + shipment.getCurrency());
            shipment.setStatus(root.path("status").asText());
            // System.out.println("Status: " + shipment.getStatus());

            Set<ShipmentPackage> shipmentPackages = new HashSet<>();
            JsonNode packagesNode = root.path("package_details");
            for (JsonNode packageNode : packagesNode) {
                Package pkg = new Package();
                pkg.setPackageId(packageNode.path("package_id").asText());
                pkg.setDescription(packageNode.path("description").asText());
                pkg.setWeight(packageNode.path("weight_kg").asDouble());
                pkg.setLength((int) packageNode.path("dimensions_cm").path("length").asDouble());
                pkg.setWidth((int) packageNode.path("dimensions_cm").path("width").asDouble());
                pkg.setHeight((int) packageNode.path("dimensions_cm").path("height").asDouble());

                ShipmentPackage shipmentPackage = new ShipmentPackage();
                shipmentPackage.setShipment(shipment);
                shipmentPackage.setPackage(pkg);
                shipmentPackages.add(shipmentPackage);

                // Print package details
                // System.out.println("Package ID: " + pkg.getPackageId());
                // System.out.println("Description: " + pkg.getDescription());
                // System.out.println("Weight: " + pkg.getWeight());
                // System.out.println("Length: " + pkg.getLength());
                // System.out.println("Width: " + pkg.getWidth());
                // System.out.println("Height: " + pkg.getHeight());
            }
            shipment.setShipmentPackages(shipmentPackages);

        } catch (Exception e) {
            e.printStackTrace();
            // Add proper exception handling here
        }
        return shipment;
    }
}
