package com.target.shipments.service;

import com.target.shipments.model.Location;
import com.target.shipments.model.Shipment;
import com.target.shipments.repository.jpa.LocationRepository;
import com.target.shipments.repository.jpa.ShipmentRepository;

// import com.target.shipments.repository.ShipmentESRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private LocationRepository locationRepository;

    // GET Requests
    public List<Shipment> getAll() {
        return shipmentRepository.findAll();
    }

    public Optional<Shipment> getById(String id) {
        return shipmentRepository.findById(id);
    }

    public List<Shipment> getByShippingMethod(String shipping_method){return shipmentRepository.findByShippingMethod(shipping_method);}
    public List<Shipment> getByCurrency(String currency){return shipmentRepository.findByCurrency(currency);}
    public List<Shipment> getByStatus(String status){return shipmentRepository.findByStatus(status);}


    public long getShippingMethodCount(String shipping_method){return shipmentRepository.countByShippingMethod(shipping_method);}
    public long getCurrencyCount(String currency){return shipmentRepository.countByCurrency(currency);}
    public long getStatusCount(String status){return shipmentRepository.countByStatus(status);}



    // POST Requests
    public Shipment save(Shipment shipment) {

        Location sender = shipment.getSender();
        Location recipient = shipment.getRecipient();

        sender.setIdentity("Sender");
        recipient.setIdentity("Recipient");
        Location findSender = locationRepository.findLocation(sender.getName(), sender.getIdentity(),
                sender.getAddress(), sender.getContact());
        Location findRecipient = locationRepository.findLocation(recipient.getName(), recipient.getIdentity(),
                recipient.getAddress(), recipient.getContact());

        if(findRecipient != null && findSender != null){
            shipment.setRecipient(findRecipient);
            shipment.setSender(findSender);
        }
        else if(findSender != null){
            shipment.setSender(findSender);
        }
        else if(findRecipient != null){
            shipment.setRecipient(findRecipient);
        }
        return shipmentRepository.save(shipment);
    }


    // DELETE Requests
    public void deleteById(String id) {
        shipmentRepository.deleteById(id);
    }
}

