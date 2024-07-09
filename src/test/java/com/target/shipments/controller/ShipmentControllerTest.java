package com.target.shipments.controller;

import com.target.shipments.model.Shipment;
import com.target.shipments.model.Location;
import com.target.shipments.service.ShipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShipmentControllerTest {

    @Mock
    private ShipmentService shipmentService;

    @InjectMocks
    private ShipmentController shipmentController;

    private MockMvc mockMvc;

    private Shipment shipment;
    private Location sender;
    private Location recipient;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(shipmentController).build();

        sender = new Location();
        sender.setLocation_id(1);
        sender.setName("Sender Name");
        sender.setAddress("Sender Address");
        sender.setContact("Sender Contact");

        recipient = new Location();
        recipient.setLocation_id(2);
        recipient.setName("Recipient Name");
        recipient.setAddress("Recipient Address");
        recipient.setContact("Recipient Contact");

        shipment = new Shipment();
        shipment.setShipment_id("1");
        shipment.setShippingMethod("Air");
        shipment.setEstimated_delivery_date("2023-12-25");
        shipment.setTotal_cost(100.0);
        shipment.setCurrency("USD");
        shipment.setStatus("Pending");
        shipment.setSender(sender);
        shipment.setRecipient(recipient);
    }

    @Test
    void testGetAll() {
        when(shipmentService.getAll()).thenReturn(Arrays.asList(shipment));

        List<Shipment> result = shipmentController.getAll();

        assertEquals(1, result.size());
        verify(shipmentService, times(1)).getAll();
    }

    @Test
    void testGetById() {
        when(shipmentService.getById("1")).thenReturn(Optional.of(shipment));

        ResponseEntity<Shipment> response = shipmentController.getById("1");

        assertEquals(ResponseEntity.ok(shipment), response);
        verify(shipmentService, times(1)).getById("1");
    }

    @Test
    void testGetByIdNotFound() {
        when(shipmentService.getById("1")).thenReturn(Optional.empty());

        ResponseEntity<Shipment> response = shipmentController.getById("1");

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(shipmentService, times(1)).getById("1");
    }

    @Test
    void testGetShipmentsByStatus() {
        when(shipmentService.getByStatus("Pending")).thenReturn(Arrays.asList(shipment));

        List<Shipment> result = shipmentController.getShipmentsByStatus("Pending");

        assertEquals(1, result.size());
        verify(shipmentService, times(1)).getByStatus("Pending");
    }

    @Test
    void testGetShipmentsByCurrency() {
        when(shipmentService.getByCurrency("USD")).thenReturn(Arrays.asList(shipment));

        List<Shipment> result = shipmentController.getShipmentsByCurrency("USD");

        assertEquals(1, result.size());
        verify(shipmentService, times(1)).getByCurrency("USD");
    }

    @Test
    void testGetShipmentsByShippingMethod() {
        when(shipmentService.getByShippingMethod("Air")).thenReturn(Arrays.asList(shipment));

        List<Shipment> result = shipmentController.getShipmenByShpMethod("Air");

        assertEquals(1, result.size());
        verify(shipmentService, times(1)).getByShippingMethod("Air");
    }

    @Test
    void testGetShipmentStatusCount() {
        when(shipmentService.getStatusCount("Pending")).thenReturn(1L);

        long result = shipmentController.getShipmentStatusCount("Pending");

        assertEquals(1L, result);
        verify(shipmentService, times(1)).getStatusCount("Pending");
    }

    @Test
    void testGetShipmentCurrencyCount() {
        when(shipmentService.getCurrencyCount("USD")).thenReturn(1L);

        long result = shipmentController.getShipmentCurrencyCount("USD");

        assertEquals(1L, result);
        verify(shipmentService, times(1)).getCurrencyCount("USD");
    }

    @Test
    void testGetShipmentShippingMethodCount() {
        when(shipmentService.getShippingMethodCount("Air")).thenReturn(1L);

        long result = shipmentController.getShipmenShpMethodCount("Air");

        assertEquals(1L, result);
        verify(shipmentService, times(1)).getShippingMethodCount("Air");
    }

    @Test
    void testCreate() {
        when(shipmentService.save(any(Shipment.class))).thenReturn(shipment);

        Shipment createdShipment = shipmentController.create(shipment);

        assertEquals(shipment, createdShipment);
        verify(shipmentService, times(1)).save(shipment);
    }

    @Test
    void testUpdate() {
        when(shipmentService.getById("1")).thenReturn(Optional.of(shipment));
        when(shipmentService.save(any(Shipment.class))).thenReturn(shipment);

        ResponseEntity<Shipment> response = shipmentController.update("1");

        assertEquals(ResponseEntity.ok(shipment), response);
        verify(shipmentService, times(1)).getById("1");
        verify(shipmentService, times(1)).save(shipment);
    }

    @Test
    void testUpdateNotFound() {
        when(shipmentService.getById("1")).thenReturn(Optional.empty());

        ResponseEntity<Shipment> response = shipmentController.update("1");

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(shipmentService, times(1)).getById("1");
        verify(shipmentService, never()).save(any(Shipment.class));
    }

//    @Test
//    void testUpdateShippingMethod() {
//        when(shipmentService.getById("1")).thenReturn(Optional.of(shipment));
//        when(shipmentService.save(any(Shipment.class))).thenReturn(shipment);
//
//        ResponseEntity<Shipment> response = shipmentController.updateShippingMethod("1", "Sea");
//
//        assertEquals(ResponseEntity.ok(shipment), response);
//        assertEquals("Sea", shipment.getShippingMethod());
//        verify(shipmentService, times(1)).getById("1");
//        verify(shipmentService, times(1)).save(shipment);
//    }

    @Test
    void testUpdateShippingMethodNotFound() {
        when(shipmentService.getById("1")).thenReturn(Optional.empty());

        ResponseEntity<Shipment> response = shipmentController.updateShippingMethod("1", "Sea");

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(shipmentService, times(1)).getById("1");
        verify(shipmentService, never()).save(any(Shipment.class));
    }

//    @Test
//    void testUpdateCurrency() {
//        when(shipmentService.getById("1")).thenReturn(Optional.of(shipment));
//        when(shipmentService.save(any(Shipment.class))).thenReturn(shipment);
//
//        ResponseEntity<Shipment> response = shipmentController.updateCurrency("1", "EUR");
//
//        assertEquals(ResponseEntity.ok(shipment), response);
//        assertEquals("EUR", shipment.getCurrency());
//        verify(shipmentService, times(1)).getById("1");
//        verify(shipmentService, times(1)).save(shipment);
//    }

    @Test
    void testUpdateCurrencyNotFound() {
        when(shipmentService.getById("1")).thenReturn(Optional.empty());

        ResponseEntity<Shipment> response = shipmentController.updateCurrency("1", "EUR");

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(shipmentService, times(1)).getById("1");
        verify(shipmentService, never()).save(any(Shipment.class));
    }

//    @Test
//    void testUpdateStatus() {
//        when(shipmentService.getById("1")).thenReturn(Optional.of(shipment));
//        when(shipmentService.save(any(Shipment.class))).thenReturn(shipment);
//
//        ResponseEntity<Shipment> response = shipmentController.updateStatus("1", "Shipped");
//
//        assertEquals(ResponseEntity.ok(shipment), response);
//        assertEquals("Shipped", shipment.getStatus());
//        verify(shipmentService, times(1)).getById("1");
//        verify(shipmentService, times(1)).save(shipment);
//    }

    @Test
    void testUpdateStatusNotFound() {
        when(shipmentService.getById("1")).thenReturn(Optional.empty());

        ResponseEntity<Shipment> response = shipmentController.updateStatus("1", "Shipped");

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(shipmentService, times(1)).getById("1");
        verify(shipmentService, never()).save(any(Shipment.class));
    }

    @Test
    void testDelete() {
        when(shipmentService.getById("1")).thenReturn(Optional.of(shipment));

        ResponseEntity<Void> response = shipmentController.delete("1");

        assertEquals(ResponseEntity.noContent().build(), response);
        verify(shipmentService, times(1)).getById("1");
        verify(shipmentService, times(1)).deleteById("1");
    }

    @Test
    void testUpdateShippingMethod() {
        when(shipmentService.getById("1")).thenReturn(Optional.of(shipment));
        when(shipmentService.save(any(Shipment.class))).thenReturn(shipment);

        ResponseEntity<Shipment> response = shipmentController.updateShippingMethod("1", "Sea");

        assertEquals(ResponseEntity.ok(shipment), response);
        assertEquals("Sea", shipment.getShippingMethod());
        verify(shipmentService, times(1)).getById("1");
        verify(shipmentService, times(1)).save(shipment);
    }

    @Test
    void testUpdateCurrency() {
        when(shipmentService.getById("1")).thenReturn(Optional.of(shipment));
        when(shipmentService.save(any(Shipment.class))).thenReturn(shipment);

        ResponseEntity<Shipment> response = shipmentController.updateCurrency("1", "EUR");

        assertEquals(ResponseEntity.ok(shipment), response);
        assertEquals("EUR", shipment.getCurrency());
        verify(shipmentService, times(1)).getById("1");
        verify(shipmentService, times(1)).save(shipment);
    }

    @Test
    void testUpdateStatus() {
        when(shipmentService.getById("1")).thenReturn(Optional.of(shipment));
        when(shipmentService.save(any(Shipment.class))).thenReturn(shipment);

        ResponseEntity<Shipment> response = shipmentController.updateStatus("1", "Shipped");

        assertEquals(ResponseEntity.ok(shipment), response);
        assertEquals("Shipped", shipment.getStatus());
        verify(shipmentService, times(1)).getById("1");
        verify(shipmentService, times(1)).save(shipment);
    }


}
