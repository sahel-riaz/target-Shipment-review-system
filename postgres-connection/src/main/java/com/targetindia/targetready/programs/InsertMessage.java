package com.targetindia.targetready.programs;

import com.targetindia.targetready.entity.Location;
import com.targetindia.targetready.entity.Shipment;
import com.targetindia.targetready.entity.Package;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import com.targetindia.targetready.util.JpaUtil;

import java.util.List;

@Slf4j
public class InsertMessage {

    /*public static void main(String[] args) {

        try(EntityManager em = JpaUtil.createEntityManager();){
            MessageInput msgIp = new MessageInput();
            Shipment msg = msgIp.getMsg();
            Location sender = msg.getLocation();
            sender.setIdentity("Sender");
            Location recipient = msg.getLocation();
            recipient.setIdentity("Recipient");
            List<Package> pd = msg.getPackages();

            log.debug("Inserting the new Shipment Message {}", msg);

            var tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(msg);
                //em.flush(); // Ensure the shipmentId is generated
                System.out.println("Message details inserted");

                em.persist(sender);
                System.out.println("Sender details inserted");

                em.persist(recipient);
                System.out.println("Recipient details inserted");

                for (Package pckDetails:  pd){
                    em.persist(pckDetails);
                }
                System.out.println("Pckg details inserted");

                tx.commit();
                System.out.println("New Shipment Details added successfully");
                log.debug("New Shipment Details added successfully");

            }
            catch(Exception e){
                log.error("Error while inserting new message", e);
                System.out.println(e);
                tx.rollback();
            }
        }

    }*/
    public static void main(String[] args) {
        try(EntityManager em = JpaUtil.createEntityManager();){
            Shipment shipment = JSONJavaConverter.getJSONtoJPA("shipmentMsg.json");
            Location sendersLocation = shipment.getSender();
            Location recipientsLocation = shipment.getRecipient();
            List<Package> pckg = shipment.getPackage_details();

            String shpId = shipment.getShipment_id();
            sendersLocation.setShipmentId(shpId);
            sendersLocation.setIdentity("Sender");

            recipientsLocation.setShipmentId(shpId);
            recipientsLocation.setIdentity("Recipient");

            System.out.println(sendersLocation);
            System.out.println(recipientsLocation);

            for (Package pd: pckg){
                pd.setShpId(shpId);
                pd.setLength(pd.getDimensions_cm().get("length"));
                pd.setWidth(pd.getDimensions_cm().get("width"));
                pd.setHeight(pd.getDimensions_cm().get("height"));

            }

            log.debug("Inserting the new Shipment Message {}", shipment);

            var tx = em.getTransaction();
            try {
                tx.begin();
                em.persist(shipment);
                em.flush(); // Ensure the shipmentId is generated
                System.out.println("Message details inserted");

                em.persist(sendersLocation);
                System.out.println("Sender details inserted");

                //em.persist(recipientsLocation);
                System.out.println("Recipient details inserted");

                for (Package pckDetails:  pckg){
                    em.persist(pckDetails);
                }
                System.out.println("Pckg details inserted");

                tx.commit();
                System.out.println("New Shipment Details added successfully");
                log.debug("New Shipment Details added successfully");

            }
            catch(Exception e){
                log.error("Error while inserting new message", e);
                System.out.println(e);
                tx.rollback();
            }
            
        }
    }

}
