package com.target.shipments.controller;

import com.target.shipments.entity.Shipment;
import com.target.shipments.entity.User;
import com.target.shipments.service.ShipmentService;
import com.target.shipments.repository.ShipmentRepository;
import com.target.shipments.repository.UserRepository;
import com.target.shipments.repository.PackageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(ShipmentController.class)
class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipmentService shipmentService;

    @MockBean
    private ShipmentRepository shipmentRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PackageRepository packageRepository;

    private Shipment sampleShipment;
    private User sampleUser;
    private com.target.shipments.entity.Package samplePackage;

    @BeforeEach
    void setUp() {
        sampleShipment = new Shipment();
        sampleShipment.setId("SH567891234");

        sampleUser = new User();
        sampleUser.setUserId(1);
        sampleUser.setName("Zade");

        samplePackage = new com.target.shipments.entity.Package();
        samplePackage.setPackageId("PKG001");
        samplePackage.setDescription("Toys");
    }

    @Test
    void testProcessShipment() throws Exception {
        Mockito.doNothing().when(shipmentService).processShipment(anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shipments/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"shipment_id\":\"SH567891234\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Shipment processed successfully"));
    }

    @Test
    void testProcessShipment_error() throws Exception {
        Mockito.doThrow(new RuntimeException("Processing error")).when(shipmentService).processShipment(anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/shipments/process")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"shipment_id\":\"SH567891234\"}"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error processing shipment: Processing error"));
    }

    @Test
    void testGetShipmentById() throws Exception {
        Mockito.when(shipmentRepository.findById("SH567891234")).thenReturn(Optional.of(sampleShipment));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shipments/SH567891234"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"SH567891234\"}"));
    }

    @Test
    void testGetShipmentById_notFound() throws Exception {
        Mockito.when(shipmentRepository.findById("SH567891234")).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shipments/SH567891234"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAllShipments() throws Exception {
        Mockito.when(shipmentRepository.findAll()).thenReturn(Collections.singletonList(sampleShipment));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shipments"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"SH567891234\"}]"));
    }



    @Test
    void testGetUserById_notFound() throws Exception {
        Mockito.when(userRepository.findById(1)).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/shipments/users/1"))
                .andExpect(status().isNotFound());
    }


}
