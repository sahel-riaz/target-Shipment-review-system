package com.target.shipments.service;

import com.target.shipments.entity.Package;
import com.target.shipments.entity.Shipment;
import com.target.shipments.entity.ShipmentPackage;
import com.target.shipments.entity.ShipmentPackageId;
import com.target.shipments.entity.User;
import com.target.shipments.repository.PackageRepository;
import com.target.shipments.repository.ShipmentPackageRepository;
import com.target.shipments.repository.ShipmentRepository;
import com.target.shipments.repository.UserRepository;
import com.target.shipments.util.ShipmentParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

@Service
public class ShipmentService {

    private static final Logger logger = LoggerFactory.getLogger(ShipmentService.class);

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private ShipmentPackageRepository shipmentPackageRepository;

    @Autowired
    private ShipmentParser shipmentParser;

    @Transactional
    public void processShipment(String shipmentData) {
        try {
            Object[] parsedData = shipmentParser.parseShipment(shipmentData);
            Shipment shipment = (Shipment) parsedData[0];
            User[] users = (User[]) parsedData[1];
            Set<Package> packages = (Set<Package>) parsedData[2];

            User sender = users[0];
            User recipient = users[1];

            if (sender != null) {
                User existingSender = userRepository.findByName(sender.getName());
                if (existingSender == null) {
                    logger.info("{} does not exist, saving new sender", sender.getName());
                    userRepository.save(sender);
                } else {
                    shipment.setSenderId(existingSender.getUserId());
                    logger.info("{} already exists", sender.getName());
                }
            }

            if (recipient != null) {
                User existingRecipient = userRepository.findByName(recipient.getName());
                if (existingRecipient == null) {
                    logger.info("{} does not exist, saving new recipient", recipient.getName());
                    userRepository.save(recipient);
                } else {
                    shipment.setRecipientId(existingRecipient.getUserId());
                    logger.info("{} already exists", recipient.getName());
                }
            }

            // Save packages
            logger.info("Saving packages");
            for (Package pkg : packages) {
                packageRepository.save(pkg);
            }

            // Save shipment
            logger.info("Saving shipment");
            shipmentRepository.save(shipment);

            // Save shipment packages
            logger.info("Saving shipment packages");
            for (Package pkg : packages) {
                // Create a new ShipmentPackage for each package
                ShipmentPackage shipmentPackage = new ShipmentPackage();
                ShipmentPackageId shipmentPackageId = new ShipmentPackageId();
                shipmentPackageId.setShipmentId(shipment.getId());
                shipmentPackageId.setPackageId(pkg.getPackageId());
                shipmentPackage.setId(shipmentPackageId);

                // Save the shipment package
                shipmentPackageRepository.save(shipmentPackage);
            }

        } catch (Exception e) {
            logger.error("Error processing shipment data: {}", shipmentData, e);
            throw e;
        }
    }
}
