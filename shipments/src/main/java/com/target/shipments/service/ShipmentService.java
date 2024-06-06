// package com.target.shipments.service;

// import com.target.shipments.entity.Package;

// import com.target.shipments.entity.*;
// import com.target.shipments.repository.*;
// import com.target.shipments.util.ShipmentParser;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;

// @Service
// public class ShipmentService {

//     @Autowired
//     private ShipmentRepository shipmentRepository;

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PackageRepository packageRepository;

//     @Autowired
//     private ShipmentPackageRepository shipmentPackageRepository;

//     @Transactional
//     public void processShipment(String shipmentData) {
//         Shipment shipment = ShipmentParser.parseShipment(shipmentData);
//         userRepository.save(shipment.getSenderId());
//         userRepository.save(shipment.getRecipientId());
//         shipmentRepository.save(shipment);

//         for (ShipmentPackage shipmentPackage : shipment.getShipmentPackages()) {
//             Package pkg = shipmentPackage.getPackageEntity();
//             packageRepository.save(pkg);
//             shipmentPackageRepository.save(shipmentPackage);
//         }
//     }
// }

// package com.target.shipments.service;

// import com.target.shipments.entity.Package;

// import com.target.shipments.entity.*;
// import com.target.shipments.repository.*;
// import com.target.shipments.util.ShipmentParser;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// @Service
// public class ShipmentService {

//     private static final Logger logger = LoggerFactory.getLogger(ShipmentService.class);

//     @Autowired
//     private ShipmentRepository shipmentRepository;

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PackageRepository packageRepository;

//     @Autowired
//     private ShipmentPackageRepository shipmentPackageRepository;

//     @Transactional
//     public void processShipment(String shipmentData) {
//         try {
//             Shipment shipment = ShipmentParser.parseShipment(shipmentData);

//             // Save sender and recipient
//             userRepository.save(shipment.getSender());
//             userRepository.save(shipment.getRecipient());

//             // Save packages
//             for (ShipmentPackage shipmentPackage : shipment.getShipmentPackages()) {
//                 Package pkg = shipmentPackage.getPackage();
//                 packageRepository.save(pkg);
//             }

//             // Save shipment
//             shipmentRepository.save(shipment);

//             // Save shipment packages
//             for (ShipmentPackage shipmentPackage : shipment.getShipmentPackages()) {
//                 shipmentPackageRepository.save(shipmentPackage);
//             }

//         } catch (Exception e) {
//             logger.error("Error processing shipment data: {}", shipmentData, e);
//             throw e; // Re-throw the exception to ensure transaction rollback
//         }
//     }
// }

package com.target.shipments.service;

import com.target.shipments.entity.Package;
import com.target.shipments.entity.*;
import com.target.shipments.repository.*;
import com.target.shipments.util.ShipmentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private ShipmentPackageRepository shipmentPackageRepository;

    @Transactional
    public void processShipment(String shipmentData) {
        Shipment shipment = ShipmentParser.parseShipment(shipmentData);

        User sender = shipment.getSender();
        User recipient = shipment.getRecipient();

        System.out.println("Sender ID: " + sender.getUserId());
        System.out.println("Sender Name: " + sender.getName());
        System.out.println("Sender Contact: " + sender.getContact());
        System.out.println("Sender Address: " + sender.getAddress());

        System.out.println("Recipient ID: " + recipient.getUserId());
        System.out.println("Recipient Name: " + recipient.getName());
        System.out.println("Recipient Contact: " + recipient.getContact());
        System.out.println("Recipient Address: " + recipient.getAddress());


        // Check if sender and recipient already exist in the database
        User existingSender = userRepository.findByName(sender.getName());
        User existingRecipient = userRepository.findByName(recipient.getName());

        // If sender doesn't exist, save it
        if (existingSender == null) {
            sender = userRepository.save(sender);
        } else {
            sender = existingSender; // Use existing sender
        }

        // If recipient doesn't exist, save it
        if (existingRecipient == null) {
            recipient = userRepository.save(recipient);
        } else {
            recipient = existingRecipient; // Use existing recipient
        }

        // Set sender and recipient IDs in the shipment
        shipment.setSender(sender);
        shipment.setRecipient(recipient);

        // Save shipment
        shipmentRepository.save(shipment);

        // Save packages
        for (ShipmentPackage shipmentPackage : shipment.getShipmentPackages()) {
            Package pkg = shipmentPackage.getPackage();
            packageRepository.save(pkg);
        }

        // Save shipment packages
        for (ShipmentPackage shipmentPackage : shipment.getShipmentPackages()) {
            shipmentPackageRepository.save(shipmentPackage);
        }
    }
}
