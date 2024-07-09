package com.target.shipments.service;

import com.target.shipments.model.*;
import com.target.shipments.repository.elasticsearch.ShipmentESRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class ShipmentServiceESTest {

    @Mock
    private ShipmentESRepository shipmentESRepository;

    @InjectMocks
    private ShipmentServiceES shipmentServiceES;

    private static final Logger logger = LoggerFactory.getLogger(ShipmentServiceES.class);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInsertShipment_ValidShipment() {
        ShipmentES shipment = createSampleShipment();
        when(shipmentESRepository.save(any())).thenReturn(shipment);

        ShipmentES savedShipment = shipmentServiceES.insertShipment(shipment);

        assertNotNull(savedShipment);
        assertEquals(shipment.getShipmentId(), savedShipment.getShipmentId());
        verify(shipmentESRepository, times(1)).save(any());
    }

    @Test
    public void testInsertShipment_NullShipment() {
        assertThrows(IllegalArgumentException.class, () -> shipmentServiceES.insertShipment(null));
        verifyNoInteractions(shipmentESRepository);
    }

    @Test
    public void testInsertShipment_InvalidCurrency() {
        ShipmentES shipment = createSampleShipment();
        shipment.setCurrency("US"); // Invalid currency format

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> shipmentServiceES.insertShipment(shipment));

        assertEquals("Invalid value for currency", exception.getMessage());
        verifyNoInteractions(shipmentESRepository);
    }

    @Test
    public void testInsertShipment_InvalidPackageDetails() {
        ShipmentES shipment = createSampleShipment();
        PackageDetail invalidPackage = shipment.getPackage_details().get(0);
        invalidPackage.setWeight_kg(-1.5); // Invalid weight

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> shipmentServiceES.insertShipment(shipment));

        assertEquals("Invalid value for weight_kg", exception.getMessage());
        verifyNoInteractions(shipmentESRepository);
    }

    @Test
    public void testGetShipmentsByStatus() {
        String status = "DELIVERED";
        List<ShipmentES> shipments = Arrays.asList(createSampleShipment());
        when(shipmentESRepository.findByStatus(status)).thenReturn(shipments);

        List<ShipmentES> foundShipments = shipmentServiceES.getShipmentsByStatus(status);

        assertNotNull(foundShipments);
        assertFalse(foundShipments.isEmpty());
        assertEquals(1, foundShipments.size());
        verify(shipmentESRepository, times(1)).findByStatus(status);
    }

    @Test
    public void testCountShipmentsByStatus() {
        String status = "IN_TRANSIT";
        when(shipmentESRepository.countByStatus(status)).thenReturn(5L);

        long count = shipmentServiceES.countShipmentsByStatus(status);

        assertEquals(5L, count);
        verify(shipmentESRepository, times(1)).countByStatus(status);
    }

    @Test
    public void testGetShipmentsByCurrency() {
        String currency = "USD";
        List<ShipmentES> shipments = Arrays.asList(createSampleShipment());
        when(shipmentESRepository.findByCurrency(currency)).thenReturn(shipments);

        List<ShipmentES> foundShipments = shipmentServiceES.getShipmentsByCurrency(currency);

        assertNotNull(foundShipments);
        assertFalse(foundShipments.isEmpty());
        assertEquals(1, foundShipments.size());
        verify(shipmentESRepository, times(1)).findByCurrency(currency);
    }

    @Test
    public void testDeleteShipmentById() {
        String shipmentId = "SH123456";

        doNothing().when(shipmentESRepository).deleteByShipmentId(shipmentId);

        shipmentServiceES.deleteShipmentById(shipmentId);

        verify(shipmentESRepository, times(1)).deleteByShipmentId(shipmentId);
    }

    @Test
    public void testValidateShipment_EmptyFields() {
        ShipmentES shipment = createSampleShipment();
        shipment.setShipmentId("");
        shipment.setCurrency("");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> shipmentServiceES.insertShipment(shipment));

        assertEquals("Invalid value for shipmentId", exception.getMessage());
    }

    @Test
    public void testValidateShipment_InvalidTotalCost() {
        ShipmentES shipment = createSampleShipment();
        shipment.setTotal_cost("-100.00");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> shipmentServiceES.insertShipment(shipment));

        assertEquals("Invalid value for total_cost", exception.getMessage());
    }



    private ShipmentES createSampleShipment() {
        ShipmentES shipment = new ShipmentES();
        shipment.setShipmentId("SH123456");
        shipment.setShipping_method("Air");
        shipment.setEstimated_delivery_date("2024-07-15");
        shipment.setTotal_cost("100.00");
        shipment.setCurrency("USD");
        shipment.setStatus("IN_TRANSIT");

        Sender sender = new Sender();
        sender.setName("John Doe");
        sender.setAddress("123 Main St");
        sender.setContact("john.doe@example.com");
        shipment.setSender(sender);

        Recipient recipient = new Recipient();
        recipient.setName("Jane Smith");
        recipient.setAddress("456 Elm St");
        recipient.setContact("jane.smith@example.com");
        shipment.setRecipient(recipient);

        PackageDetail packageDetail = new PackageDetail();
        packageDetail.setPackage_id("PKG123");
        packageDetail.setDescription("Sample package");
        packageDetail.setWeight_kg(2.5);
        packageDetail.setDimensions_cm(new Dimensions(10, 20, 15));
        shipment.setPackage_details(Arrays.asList(packageDetail));

        return shipment;
    }


}
