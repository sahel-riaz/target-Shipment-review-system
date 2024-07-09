package com.target.shipments.controller;

import com.target.shipments.model.ShipmentES;
import com.target.shipments.service.ShipmentServiceES;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ShipmentESControllerTest {

    @Mock
    private ShipmentServiceES shipmentService;

    @InjectMocks
    private ShipmentESController shipmentESController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllShipments() {
        List<ShipmentES> shipments = Arrays.asList(
                new ShipmentES("1", "Air", "2024-08-01", "100.00", "USD", "IN_TRANSIT", null, null),
                new ShipmentES("2", "Sea", "2024-08-05", "200.00", "EUR", "DELIVERED", null, null)
        );

        when(shipmentService.getShipments()).thenReturn(shipments);

        Iterable<ShipmentES> result = shipmentESController.findAll();

        assertEquals(shipments.size(), ((List<ShipmentES>) result).size());
        verify(shipmentService, times(1)).getShipments();
    }

    @Test
    public void testFindByStatus() {
        String status = "DELIVERED";
        List<ShipmentES> shipments = Arrays.asList(
                new ShipmentES("2", "Sea", "2024-08-05", "200.00", "EUR", "DELIVERED", null, null)
        );

        when(shipmentService.getShipmentsByStatus(status)).thenReturn(shipments);

        List<ShipmentES> result = shipmentESController.findByStatus(status);

        assertEquals(shipments.size(), result.size());
        verify(shipmentService, times(1)).getShipmentsByStatus(status);
    }

    @Test
    public void testCountShipmentsByStatus() {
        String status = "IN_TRANSIT";
        long count = 2;

        when(shipmentService.countShipmentsByStatus(status)).thenReturn(count);

        long result = shipmentESController.countShipmentsByStatus(status);

        assertEquals(count, result);
        verify(shipmentService, times(1)).countShipmentsByStatus(status);
    }

    @Test
    public void testFindByCurrency() {
        String currency = "USD";
        List<ShipmentES> shipments = Arrays.asList(
                new ShipmentES("1", "Air", "2024-08-01", "100.00", "USD", "IN_TRANSIT", null, null)
        );

        when(shipmentService.getShipmentsByCurrency(currency)).thenReturn(shipments);

        List<ShipmentES> result = shipmentESController.findByCurrency(currency);

        assertEquals(shipments.size(), result.size());
        verify(shipmentService, times(1)).getShipmentsByCurrency(currency);
    }

    @Test
    public void testDeleteShipmentById() {
        String shipmentId = "1";

        shipmentESController.deleteShipmentById(shipmentId);

        verify(shipmentService, times(1)).deleteShipmentById(shipmentId);
    }
}
