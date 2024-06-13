package com.target.shipments.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import com.target.shipments.entity.Package;
import com.target.shipments.entity.Shipment;
import com.target.shipments.entity.ShipmentPackage;
import com.target.shipments.entity.User;
import com.target.shipments.repository.PackageRepository;
import com.target.shipments.repository.ShipmentPackageRepository;
import com.target.shipments.repository.ShipmentRepository;
import com.target.shipments.repository.UserRepository;
import com.target.shipments.service.ShipmentService;
import com.target.shipments.util.ShipmentParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ShipmentServiceTest {

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PackageRepository packageRepository;

    @Mock
    private ShipmentPackageRepository shipmentPackageRepository;

    @Mock
    private ShipmentParser shipmentParser;

    @InjectMocks
    private ShipmentService shipmentService;

    @Test
    public void testProcessShipment() {
        String shipmentData = "{\n" +
                "  \"shipment_id\": \"SH512391234\",\n" +
                "  \"sender\": {\n" +
                "    \"name\": \"Brad Pit\",\n" +
                "    \"address\": \"754 Maple Street, Berlin, Germany\",\n" +
                "    \"contact\": \"brad.pit@gmail.com\"\n" +
                "  },\n" +
                "  \"recipient\": {\n" +
                "    \"name\": \"Joshua\",\n" +
                "    \"address\": \"465 hellen Street, Toronto, Canada\",\n" +
                "    \"contact\": \"josh.joshua@gmail.com\"\n" +
                "  },\n" +
                "  \"package_details\": [\n" +
                "    {\n" +
                "      \"package_id\": \"PKG004\",\n" +
                "      \"description\": \"Books\",\n" +
                "      \"weight_kg\": 10.8,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 20,\n" +
                "        \"width\": 5,\n" +
                "        \"height\": 6\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"package_id\": \"PKG005\",\n" +
                "      \"description\": \"Electrical\",\n" +
                "      \"weight_kg\": 8.6,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 32,\n" +
                "        \"width\": 15,\n" +
                "        \"height\": 12\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"package_id\": \"PKG006\",\n" +
                "      \"description\": \"Clothing\",\n" +
                "      \"weight_kg\": 26.5,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 16,\n" +
                "        \"width\": 30,\n" +
                "        \"height\": 20\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"shipping_method\": \"Express\",\n" +
                "  \"estimated_delivery_date\": \"2024-07-02\",\n" +
                "  \"total_cost\": 60.00,\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"status\": \"In transit\"\n" +
                "}";

        // Mocking the parsed data
        Shipment shipment = new Shipment();
        User sender = new User();
        sender.setName("Brad Pit");
        User recipient = new User();
        recipient.setName("Joshua");

        User[] users = {sender, recipient};

        Set<Package> packages = new HashSet<>();
        Package package1 = new Package();
        package1.setPackageId("PKG004");
        packages.add(package1);

        Package package2 = new Package();
        package2.setPackageId("PKG005");
        packages.add(package2);

        Package package3 = new Package();
        package3.setPackageId("PKG006");
        packages.add(package3);

        when(shipmentParser.parseShipment(shipmentData)).thenReturn(new Object[]{shipment, users, packages});
        when(userRepository.findByName(anyString())).thenReturn(null);

        // Call the method under test
        shipmentService.processShipment(shipmentData);

        // Verify interactions
        verify(userRepository, times(1)).save(sender);
        verify(userRepository, times(1)).save(recipient);
        verify(packageRepository, times(3)).save(any(Package.class));
        verify(shipmentRepository, times(1)).save(shipment);
        verify(shipmentPackageRepository, times(3)).save(any(ShipmentPackage.class));
    }
    @Test
    public void testProcessShipment_ExistingUsers() {
        String shipmentData = "{\n" +
                "  \"shipment_id\": \"SH512391234\",\n" +
                "  \"sender\": {\n" +
                "    \"name\": \"Brad Pit\",\n" +
                "    \"address\": \"754 Maple Street, Berlin, Germany\",\n" +
                "    \"contact\": \"brad.pit@gmail.com\"\n" +
                "  },\n" +
                "  \"recipient\": {\n" +
                "    \"name\": \"Joshua\",\n" +
                "    \"address\": \"465 hellen Street, Toronto, Canada\",\n" +
                "    \"contact\": \"josh.joshua@gmail.com\"\n" +
                "  },\n" +
                "  \"package_details\": [\n" +
                "    {\n" +
                "      \"package_id\": \"PKG004\",\n" +
                "      \"description\": \"Books\",\n" +
                "      \"weight_kg\": 10.8,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 20,\n" +
                "        \"width\": 5,\n" +
                "        \"height\": 6\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"package_id\": \"PKG005\",\n" +
                "      \"description\": \"Electrical\",\n" +
                "      \"weight_kg\": 8.6,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 32,\n" +
                "        \"width\": 15,\n" +
                "        \"height\": 12\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"package_id\": \"PKG006\",\n" +
                "      \"description\": \"Clothing\",\n" +
                "      \"weight_kg\": 26.5,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 16,\n" +
                "        \"width\": 30,\n" +
                "        \"height\": 20\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"shipping_method\": \"Express\",\n" +
                "  \"estimated_delivery_date\": \"2024-07-02\",\n" +
                "  \"total_cost\": 60.00,\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"status\": \"In transit\"\n" +
                "}";

        // Mocking the parsed data
        Shipment shipment = new Shipment();
        User sender = new User();
        sender.setName("Brad Pit");
        User recipient = new User();
        recipient.setName("Joshua");

        User[] users = {sender, recipient};

        Set<Package> packages = new HashSet<>();
        Package package1 = new Package();
        package1.setPackageId("PKG004");
        packages.add(package1);

        Package package2 = new Package();
        package2.setPackageId("PKG005");
        packages.add(package2);

        Package package3 = new Package();
        package3.setPackageId("PKG006");
        packages.add(package3);

        when(shipmentParser.parseShipment(shipmentData)).thenReturn(new Object[]{shipment, users, packages});

        // Mock UserRepository to return existing users
        when(userRepository.findByName("Brad Pit")).thenReturn(new User());
        when(userRepository.findByName("Joshua")).thenReturn(new User());

        // Call the method under test
        shipmentService.processShipment(shipmentData);

        // Verify interactions
        verify(userRepository, never()).save(sender); // Existing user, should not save
        verify(userRepository, never()).save(recipient); // Existing user, should not save
        verify(packageRepository, times(3)).save(any(Package.class));
        verify(shipmentRepository, times(1)).save(shipment);
        verify(shipmentPackageRepository, times(3)).save(any(ShipmentPackage.class));
    }
    @Test
    public void testProcessShipment_Exception() {
        String shipmentData = "{\n" +
                "  \"shipment_id\": \"SH512391234\",\n" +
                "  \"sender\": {\n" +
                "    \"name\": \"Brad Pit\",\n" +
                "    \"address\": \"754 Maple Street, Berlin, Germany\",\n" +
                "    \"contact\": \"brad.pit@gmail.com\"\n" +
                "  },\n" +
                "  \"recipient\": {\n" +
                "    \"name\": \"Joshua\",\n" +
                "    \"address\": \"465 hellen Street, Toronto, Canada\",\n" +
                "    \"contact\": \"josh.joshua@gmail.com\"\n" +
                "  },\n" +
                "  \"package_details\": [\n" +
                "    {\n" +
                "      \"package_id\": \"PKG004\",\n" +
                "      \"description\": \"Books\",\n" +
                "      \"weight_kg\": 10.8,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 20,\n" +
                "        \"width\": 5,\n" +
                "        \"height\": 6\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"package_id\": \"PKG005\",\n" +
                "      \"description\": \"Electrical\",\n" +
                "      \"weight_kg\": 8.6,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 32,\n" +
                "        \"width\": 15,\n" +
                "        \"height\": 12\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"package_id\": \"PKG006\",\n" +
                "      \"description\": \"Clothing\",\n" +
                "      \"weight_kg\": 26.5,\n" +
                "      \"dimensions_cm\": {\n" +
                "        \"length\": 16,\n" +
                "        \"width\": 30,\n" +
                "        \"height\": 20\n" +
                "      }\n" +
                "    }\n" +
                "  ],\n" +
                "  \"shipping_method\": \"Express\",\n" +
                "  \"estimated_delivery_date\": \"2024-07-02\",\n" +
                "  \"total_cost\": 60.00,\n" +
                "  \"currency\": \"INR\",\n" +
                "  \"status\": \"In transit\"\n" +
                "}";

        // Mocking the parsed data
        Shipment shipment = new Shipment();
        User sender = new User();
        sender.setName("Brad Pit");
        User recipient = new User();
        recipient.setName("Joshua");

        User[] users = {sender, recipient};

        Set<Package> packages = new HashSet<>();
        Package package1 = new Package();
        package1.setPackageId("PKG004");
        packages.add(package1);

        Package package2 = new Package();
        package2.setPackageId("PKG005");
        packages.add(package2);

        Package package3 = new Package();
        package3.setPackageId("PKG006");
        packages.add(package3);

        when(shipmentParser.parseShipment(shipmentData)).thenReturn(new Object[]{shipment, users, packages});

        // Mock UserRepository to throw an exception
        when(userRepository.findByName(anyString())).thenThrow(new RuntimeException("User repository exception"));

        // Call the method under test and expect it to throw an exception
        assertThrows(RuntimeException.class, () -> shipmentService.processShipment(shipmentData));

        // Verify interactions
        verify(userRepository, times(1)).findByName("Brad Pit");
        verify(userRepository, never()).save(any(User.class)); // No users should be saved if an exception occurs
        verify(packageRepository, never()).save(any(Package.class)); // No packages should be saved if an exception occurs
        verify(shipmentRepository, never()).save(any(Shipment.class)); // No shipment should be saved if an exception occurs
        verify(shipmentPackageRepository, never()).save(any(ShipmentPackage.class)); // No shipment packages should be saved if an exception occurs
    }

}
