package com.target.shipments.service;

import com.target.shipments.model.Location;
import com.target.shipments.model.Package;
import com.target.shipments.model.Shipment;
import com.target.shipments.repository.jpa.LocationRepository;
import com.target.shipments.repository.jpa.ShipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShipmentServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(ShipmentServiceTest.class);

    @Mock
    private ShipmentRepository shipmentRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private ShipmentService shipmentService;

    private Shipment shipment;
    private Location sender;
    private Location recipient;
    private Package pkg;

    @BeforeEach
    void setUp() {
        sender = new Location(null, "Sender Name", "Sender", "Sender Address", "Sender Contact", null, null);
        recipient = new Location(null, "Recipient Name", "Recipient", "Recipient Address", "Recipient Contact", null, null);
        pkg = new Package("pkg1", "Description", 10.0, 10, 10, 10, null);
        shipment = new Shipment("1", "Air", "2024-12-01", 100.0, "USD", "Pending", sender, recipient, Collections.singletonList(pkg));
    }


    @Test
    void testGetAllShipments() {
        when(shipmentRepository.findAll()).thenReturn(Collections.singletonList(shipment));

        logger.info("Testing getAllShipments");
        List<Shipment> shipments = shipmentService.getAll();

        assertNotNull(shipments);
        assertEquals(1, shipments.size());
        verify(shipmentRepository, times(1)).findAll();
        logger.info("getAllShipments test passed");
    }

    @Test
    void testGetShipmentById() {
        when(shipmentRepository.findById("1")).thenReturn(Optional.of(shipment));

        logger.info("Testing getShipmentById");
        Optional<Shipment> foundShipment = shipmentService.getById("1");

        assertTrue(foundShipment.isPresent());
        assertEquals(shipment, foundShipment.get());
        verify(shipmentRepository, times(1)).findById("1");
        logger.info("getShipmentById test passed");
    }

    @Test
    void testSaveShipment() {
        when(locationRepository.findLocation(anyString(), anyString(), anyString(), anyString())).thenReturn(null);
        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        logger.info("Testing saveShipment");
        Shipment savedShipment = shipmentService.save(shipment);

        assertNotNull(savedShipment);
        assertEquals(shipment, savedShipment);
        verify(locationRepository, times(2)).findLocation(anyString(), anyString(), anyString(), anyString());
        verify(shipmentRepository, times(1)).save(any(Shipment.class));
        logger.info("saveShipment test passed");
    }

    @Test
    void testDeleteShipmentById() {
        logger.info("Testing deleteShipmentById");
        doNothing().when(shipmentRepository).deleteById("1");

        shipmentService.deleteById("1");

        verify(shipmentRepository, times(1)).deleteById("1");
        logger.info("deleteShipmentById test passed");
    }

    @Test
    void testValidateShipmentInvalidId() {
        logger.info("Testing validateShipment with invalid shipment_id");
        shipment.setShipment_id("");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shipmentService.save(shipment);
        });

        String expectedMessage = "Invalid value for shipment_id";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("validateShipment with invalid shipment_id test passed");
    }

    @Test
    void testValidateShipmentInvalidDate() {
        logger.info("Testing validateShipment with invalid estimated_delivery_date");
        shipment.setEstimated_delivery_date("invalid-date");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shipmentService.save(shipment);
        });

        String expectedMessage = "Invalid value for estimated_delivery_date";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("validateShipment with invalid estimated_delivery_date test passed");
    }

    @Test
    void testValidateShipmentInvalidCurrency() {
        logger.info("Testing validateShipment with invalid currency");
        shipment.setCurrency("invalid");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shipmentService.save(shipment);
        });

        String expectedMessage = "Invalid value for currency";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("validateShipment with invalid currency test passed");
    }

    @Test
    void testValidateShipmentNullSender() {
        logger.info("Testing validateShipment with null sender");
        shipment.setSender(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shipmentService.save(shipment);
        });

        String expectedMessage = "Sender cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("validateShipment with null sender test passed");
    }

    @Test
    void testValidateShipmentNullRecipient() {
        logger.info("Testing validateShipment with null recipient");
        shipment.setRecipient(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            shipmentService.save(shipment);
        });

        String expectedMessage = "Recipient cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
        logger.info("validateShipment with null recipient test passed");
    }

    @Test
    void testValidateShipmentNoPackageDetails() {
        logger.info("Testing validateShipment with no package details");
        shipment.setPackage_details(null);

        when(locationRepository.findLocation(anyString(), anyString(), anyString(), anyString())).thenReturn(null);
        when(shipmentRepository.save(any(Shipment.class))).thenReturn(shipment);

        Shipment savedShipment = shipmentService.save(shipment);

        assertNotNull(savedShipment);
        assertNotNull(savedShipment.getPackage_details());
        assertTrue(savedShipment.getPackage_details().isEmpty());
        verify(locationRepository, times(2)).findLocation(anyString(), anyString(), anyString(), anyString());
        verify(shipmentRepository, times(1)).save(any(Shipment.class));
        logger.info("validateShipment with no package details test passed");
    }
}
